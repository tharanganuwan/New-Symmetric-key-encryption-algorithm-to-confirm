package codes;

import java.util.Random;

public class Algo {

    static String randomElement1;
    static String randomElement2;

//    String symbols[] = {"ꇺ","ෆ","☺","ꕤ","ᙏ","ʬ","ଳ","ᴗ","✿","♡","യ","ɞ","ꔛ","☄","⍤","◯","ε","з","ꔚ","✦","✱","ꊞ","↺","°","•","⁀","➷","♡","୨","୧"};
    String letters[] = {"@","$","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","?","%"};


    public static void main(String args[])
    {
        String text = "Hi My name is tharanga Nuwan Kumara Kumara Amal";
        String key = "AABB09182736CCDD";

        Random random = new Random();
        int randomNum1 = random.nextInt(9);
        int randomNum2 = random.nextInt(10);
        while(randomNum1==randomNum2 || randomNum1>randomNum2){
            randomNum2 = random.nextInt(10);
        }
        int randomNum3 = random.nextInt(10);   // start ponit of Symbol
        System.out.println("random num 1 :"+randomNum1);
        System.out.println("random num 2 :"+randomNum2);
        System.out.println("random num 3 :"+randomNum3);

        Algo algo = new Algo();
        algo.encrypt(text,randomNum1,randomNum2,randomNum3);
    }
    void encrypt( String text, int r1,int r2,int r3){

        Algo algo = new Algo();
        String out=algo.conAscii(text,5); //add count
        String textArray[]=algo.divided(out);
        textArray=algo.conHex(textArray);
        textArray=  algo.shifting(textArray,3);     //shift count
        textArray=algo.removeElements(textArray,r1,r2);     // remove 2 columns
        text=algo.arrayToString(textArray);
        text=algo.convertSymbol(text,r3);     // give random start number Convert to Symbol
        System.out.println(text);
    }
    String convertSymbol(String text,int start){

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

        for(int i=0;i<numArray.length;i++){
            String parts=numArray[i];
            String a=letters[i];
            text= text.replace(parts,a);
        }
        return text;
    }

    String arrayToString(String array[]){
        String text="";
        for(int i=0;i<array.length;i++){
            text=text+array[i];
        }
        return text;
    }

    String[] removeElements(String array[],int r1,int r2){
        int count=0;
        String []newArray= new String[10];
        for(int i=0;i<array.length;i++){
            if(i==r1){
                randomElement1=array[i];
            }else if(i==r2){
                randomElement2=array[i];
            }else {
                newArray[count]=array[i];
                count++;
            }
        }
        System.out.println("random Element 1 : "+randomElement1);
        System.out.println("random Element 2 : "+randomElement2);
        return newArray;
    }

    String[] shifting(String[] text,int count){
        for(int k=0;k<count;k++){
            String temp = text[text.length-1];
            for (int i = (text.length - 2); i >= 0; i--) {
                text[i+1] = text[i];
            }
            text[0] = temp;
        }
        return text;
    }

    String[] conHex(String text[]){
        for(int i=0;i<text.length;i++){
            long a= Long.parseLong(text[i]);
            String s = Long.toHexString(a);
            while(s.length()<14){
                s="0"+s;
            }
            text[i]=s;
        }
        return text;
    }

    public String conAscii(String text,int add){
        String temp="";
        String out="";
        for(int i=0;i<text.length();i++){
            temp=String.valueOf((int)text.charAt(i)+add);         // add 5 to ascii code

            if(temp.length()!=3){
                temp="0"+temp;
            }
            out=out+temp;
        }
        while(out.length()<192){
            out=out+"9";
        }
        return out;
    }

    public String[] divided(String str){
        int len = str.length(),n = 12,temp = 0,chars = len/n;
        String[] equalStr = new String [n];

        for(int i = 0; i < len; i = i+chars) {
            String part = str.substring(i, i+chars);
            equalStr[temp] = part;
            temp++;
        }
        return  equalStr;
    }
}