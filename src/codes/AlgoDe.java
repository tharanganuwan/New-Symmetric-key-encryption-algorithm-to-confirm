package codes;

public class AlgoDe {
//    String symbols[] = {"ꇺ","ෆ","☺","ꕤ","ᙏ","ʬ","ଳ","ᴗ","✿","♡","യ","ɞ","ꔛ","☄","⍤","◯","ε","з","ꔚ","✦","✱","ꊞ","↺","°","•","⁀","➷","♡","୨","୧"};
    String letters[] = {"@","$","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","?","%"};

    public static void main(String args[])
    {
        AlgoDe algo = new AlgoDe();

        String text = "2386f26fc0ffff2386f26fc0ffff2386f26fc0ffff02bd4fcbb$08c19L9dd31f7c55@a0c4%104J%XKd71d8fH2@a0c4%0Mb7107db441022323b191e3b3793f6cf";

        String key = "AABB09182736CCDD";
        int randomNum1=4;
        int randomNum2=7;
        int randomNum3=3;
        String randomElement1="0d2f994fbb3aab";
        String randomElement2="00b945899653ba";

        algo.decrypt(text,randomNum1,randomNum2,randomNum3,randomElement1,randomElement2);
    }
    void decrypt(String text,int r1,int r2,int r3,String e1,String e2) {

        String arr[]=new String[10];

        AlgoDe algo = new AlgoDe();
        text = algo.removeSymbols(text,r3);
        arr=algo.StringToArray(text);
        arr=algo.addRemoveElements(arr,r1,r2,e1,e2);
        arr=algo.shifting(arr,3);   //shift 3 rows
        text = algo.hecDecimal(arr);
        String out=algo.deConAscii(text,5); // decrement assci value
        System.out.println(out);

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
