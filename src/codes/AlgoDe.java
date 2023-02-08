package codes;

public class AlgoDe {

    String symbols[] = {"~","!","#","^","*","(",")","-","+","{","}","[","]","_","?",":"};
    String letters[] = {"@","$","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","&","%"};

    public static void main(String args[])
    {
        AlgoDe algo = new AlgoDe();

        String text = "2386f26fc0::2386f26fc0::02bd4fc[0P8c0d2f{4f[3}b19J9_31f7c(~b9K89%53ba0VId71d8fF203a0c4Z0Kb7107db*10#323b191e3b37&f6cf";

        String key = "AABB09182736CCDD";
        int randomNum1=8;
        int randomNum2=9;
        int randomNum3=8 ;
        String randomElement1="07839d71d8f302";
        String randomElement2="03a0c490045b71";

        algo.decrypt(text,randomNum1,randomNum2,randomNum3,randomElement1,randomElement2);
    }
    void decrypt(String text,int r1,int r2,int r3,String e1,String e2) {

        String arr[]=new String[10];

        AlgoDe algo = new AlgoDe();

        text=algo.deCompress(text);
        text = algo.removeSymbols(text,r3);
        arr=algo.StringToArray(text);
        arr=algo.addRemoveElements(arr,r1,r2,e1,e2);
        arr=algo.shifting(arr,3);   //shift 3 rows
        text = algo.hecDecimal(arr);
        String out=algo.deConAscii(text,5); // decrement assci value
        System.out.println(out);

    }

    String deCompress(String text){

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
            }
            else {
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
        int len = str.length(),n = 10,temp = 0,chars = len/n;
        String[] equalStr = new String [n];
        for(int i = 0; i < len; i = i+chars) {
            String part = str.substring(i, i+chars);
            equalStr[temp] = part;
            temp++;
        }
        return  equalStr;
    }

    String[] addRemoveElements(String array[],int r1,int r2, String e1,String e2){
        String []newArray=new String[12];
        int count=0;
        for(int i=0;i<12;i++){
            if(i==r1){
                newArray[i]=e1;
            }else if(i==r2){
                newArray[i]=e2;
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
        int n = 64;
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
