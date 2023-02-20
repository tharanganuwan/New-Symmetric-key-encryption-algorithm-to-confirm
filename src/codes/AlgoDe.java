package codes;

public class AlgoDe {

    String symbols[] = {"~","!","#","^","*","(",")","-","+","{","}","[","]","_","?",":"};
    String letters[] = {"@","$","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","&","%"};

    public static void main(String args[])
    {
        AlgoDe algo = new AlgoDe();

        String text = "0^ca691fR}10ea8ab7a^cfea04Sd452fYea~deH0db1faUe0ea07dR409ead04Seb9b6eb7d61d2d25Ac9bKe$c37937e07:fDYfEfc0::DYfEfc0::DYfEfc0::DYfEfc0::DYfEfc0::DYfEfc0::DYfEfc0::DYfEfc0::DYfEfc0::DYfEfc0::DYfEfc0::";

        String key = "AABB09182736CCDD";
        int randomNum1=1;
        int randomNum2=9;
        int randomNum3=14;
        int randomNum4=18;
        int randomNum5=20;
        int randomCount=7;
        int randomShift=0;
        int randomStart=8;
        String randomElement1="061427a754fba0";
        String randomElement2="2386f26fc0ffff";
        String randomElement3="2386f26fc0ffff";
        String randomElement4="2386f26fc0ffff";
        String randomElement5="2386f26fc0ffff";

        algo.decrypt(text,randomNum1,randomNum2,randomNum3,randomNum4,randomNum5,randomElement1,randomElement2,randomElement3,randomElement4,randomElement5,randomCount,randomShift,randomStart);
    }
    void decrypt(String text,int r1,int r2,int r3,int r4,int r5,String e1,String e2,String e3,String e4,String e5,int randomCount,int randomShift,int randomStart) {

        String arr[]=new String[10];


        AlgoDe algo = new AlgoDe();

        text=algo.deCompress(text);
        text = algo.removeSymbols(text,randomStart);
        arr=algo.StringToArray(text);
        arr=algo.addRemoveElements(arr,r1,r2,r3,r4,r5,e1,e2,e3,e4,e5);
        arr=algo.shifting(arr,randomShift);   //shift 3 rows
        text = algo.hecDecimal(arr);
        String out=algo.deConAscii(text,randomCount); // decrement assci value
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
        int len = str.length(),n = 19,temp = 0,chars = len/n;
        String[] equalStr = new String [n];
        for(int i = 0; i < len; i = i+chars) {
            String part = str.substring(i, i+chars);
            equalStr[temp] = part;
            temp++;
        }
        return  equalStr;
    }

    String[] addRemoveElements(String array[],int r1,int r2,int r3,int r4,int r5, String e1,String e2,String e3,String e4,String e5){
        String []newArray=new String[24];
        int count=0;
        for(int i=0;i<24;i++){
            if(i==r1){
                newArray[i]=e1;
            }else if(i==r2){
                newArray[i]=e2;
            }
            else if(i==r3){
                newArray[i]=e3;
            }
            else if(i==r4){
                newArray[i]=e4;
            }
            else if(i==r5){
                newArray[i]=e5;
            }
            else {
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
