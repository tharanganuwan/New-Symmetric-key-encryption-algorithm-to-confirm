package codes;

import java.util.Random;

public class Algo {

    static String randomElement1;
    static String randomElement2;

    public static void main(String args[])
    {
        String text = "Hi My name is tharanga Nuwan Kumara";
        String key = "AABB09182736CCDD";

        Random random = new Random();
        int randomNum1 = random.nextInt(9);
        int randomNum2 = random.nextInt(10);
        while(randomNum1==randomNum2 || randomNum1>randomNum2){
            randomNum2 = random.nextInt(10);
        }
        System.out.println(randomNum1);
        System.out.println(randomNum2);
        Algo algo = new Algo();
        algo.encrypt(text,randomNum1,randomNum2);
    }
    void encrypt( String text, int r1,int r2){

        Algo algo = new Algo();
        String out=algo.conAscii(text,5); //add count
        String textArray[]=algo.divided(out);
        textArray=algo.conHex(textArray);
        textArray=  algo.shifting(textArray,3);     //shift count

        for(int i = 0; i < textArray.length; i++) {
            System.out.println(textArray[i]);
        }
        System.out.println("\n");
        textArray=algo.removeElements(textArray,r1,r2);

        for(int i = 0; i < textArray.length; i++) {
            System.out.println(textArray[i]);
        }

        System.out.println("\n");

        System.out.println(randomElement1);
        System.out.println(randomElement2);


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