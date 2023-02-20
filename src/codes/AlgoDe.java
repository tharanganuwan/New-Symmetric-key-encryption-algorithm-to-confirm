package codes;

import objects.RandomNumbers;
import objects.RandomText;

public class AlgoDe {
    static RandomText randomText = new RandomText();
    String symbols[] = {"~","!","#","^","*","(",")","-","+","{","}","[","]","_","?",":"};
    String letters[] = {"@","$","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","&","%"};

    public static void main(String args[]) {
        String text = "2386f26fc0::2386f26fc0::2386f26fc0::0HedbdX4_d506ca3c6NQba815c57ae45f$3c047b0{926f1dJe{6598f0c75Qde^9d29ad45Q5V54c_c9af90*48?42d4bef039AbZa9B9b2386f26fc0::2386f26fc0::2386f26fc0::2386f26fc0::2386f26fc0::2386f26fc0::2386f26fc0::";
        String key = "2386f26fc0ffff115bd4ccf6bdeff047b20e19d3b0a92386f26fc0ffff1018202386f26fc0ffff947";
        AlgoDe algo = new AlgoDe();
        RandomNumbers numbers = algo.identifyKey(key);
        algo.decrypt(text,numbers,randomText);
    }
    public RandomNumbers identifyKey(String key){
        RandomNumbers numbers = new RandomNumbers();
        randomText.setRandomElement1(key.substring(0,14));
        randomText.setRandomElement2(key.substring(15,29));
        randomText.setRandomElement3(key.substring(29,43));
        randomText.setRandomElement4(key.substring(44,58));
        randomText.setRandomElement5(key.substring(64,78));
        numbers.setRandomNum1(Integer.parseInt(key.substring(14,15)));
        numbers.setRandomNum2(Integer.parseInt(key.substring(43,44)));
        numbers.setRandomNum3(Integer.parseInt(key.substring(58,60)));
        numbers.setRandomNum4(Integer.parseInt(key.substring(60,62)));
        numbers.setRandomNum5(Integer.parseInt(key.substring(62,64)));
        numbers.setRandomCount(Integer.parseInt(key.substring(78,79)));
        numbers.setRandomShift(Integer.parseInt(key.substring(79,80)));
        numbers.setRandomStart(Integer.parseInt(key.substring(80,81)));
        return numbers;
    }

    public void decrypt(String text, RandomNumbers numbers, RandomText rText) {
        String arr[]=new String[10];
        AlgoDe algo = new AlgoDe();
        text=algo.deCompress(text);
        text = algo.removeSymbols(text,numbers.getRandomStart());
        arr=algo.StringToArray(text);
        arr=algo.addRemoveElements(arr,numbers,rText);
        arr=algo.shifting(arr,numbers.getRandomShift());   //shift 3 rows
        text = algo.hecDecimal(arr);
        String out=algo.deConAscii(text,numbers.getRandomCount()); // decrement assci value
        System.out.println(out);

    }

    public String deCompress(String text){
        for(int i=0;i<10;i++){
                String value = (String.valueOf(i)+String.valueOf(i));
                text= text.replace(symbols[i],value);
            }
            text= text.replace(symbols[10],"aa");
            text= text.replace(symbols[11],"bb");
            text= text.replace(symbols[12],"cc");
            text= text.replace(symbols[13],"dd");
            text= text.replace(symbols[14],"ee");
            text= text.replace(symbols[15],"ff");
        return text;
    }

    public String removeSymbols(String text,int start){
        String numArray[] = new String[30];
        int count=0;
        for(int i=start;i<90+start;i=i+3){
            if(i<10){
                numArray[count]="0"+String.valueOf(i) ;
            }else {
                numArray[count]=String.valueOf(i) ;
            }
            count++;
        }
        for(int i=0;i<letters.length;i++){
            String parts=letters[i];
            String a=numArray[i];
            text= text.replace(parts,a);
        }
        return text;
    }

    public String[] StringToArray(String str){
        int len = str.length(),n = 19,temp = 0,chars = len/n;
        String[] equalStr = new String [n];
        for(int i = 0; i < len; i = i+chars) {
            String part = str.substring(i, i+chars);
            equalStr[temp] = part;
            temp++;
        }
        return  equalStr;
    }

    String[] addRemoveElements(String array[],RandomNumbers numbers, RandomText rText){
        String []newArray=new String[24];
        int count=0;
        for(int i=0;i<24;i++){
            if(i==numbers.getRandomNum1()){
                newArray[i]=rText.getRandomElement1();
            }else if(i==numbers.getRandomNum2()){
                newArray[i]=rText.getRandomElement2();
            }else if(i==numbers.getRandomNum3()){
                newArray[i]=rText.getRandomElement3();
            }else if(i==numbers.getRandomNum4()){
                newArray[i]=rText.getRandomElement4();
            }else if(i==numbers.getRandomNum5()){
                newArray[i]=rText.getRandomElement5();
            }else {
                newArray[i]=array[count];
                count++;
            }
        }
        return newArray;
    }

    String[] shifting(String[] text,int count){
        for(int k=0;k<count;k++){
            String temp = text[0];
            for (int i = (0); i <= text.length - 2; i++) {
                 text[i]=text[i+1];
            }
            text[text.length-1] = temp;
        }
        return text;
    }

    String hecDecimal(String list[]){
        String a = "";
        String b="";
        for(int i=0;i<list.length;i++){
            long decimal=Long.parseLong(list[i],16);
            a=String.valueOf(decimal);
            while (a.length()<16){
                a="0"+a;
            }
            b=b+a;
        }
        return b;
    }

     String deConAscii(String text,int sub){
        String fText="";
        int len = text.length();
        int n = 128;
        int temp = 0, chars = len/n;
        String[] equalStr = new String [n];
        for(int i = 0; i < len; i = i+chars) {
            String part = text.substring(i, i+chars);
            equalStr[temp] = part;
            temp++;
        }
        for(int i=0;i<equalStr.length;i++){
            int asciiVal = Integer.parseInt(equalStr[i]);
            if(asciiVal!=999)
                fText=fText+ (char) ((char) asciiVal - sub);
        }
        return fText;
     }
}
