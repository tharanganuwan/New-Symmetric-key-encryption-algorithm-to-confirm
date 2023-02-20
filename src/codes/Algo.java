package codes;

import objects.RandomNumbers;
import objects.RandomText;

import java.util.Random;

public class Algo {

    static String randomElement1;
    static String randomElement2;
    static String randomElement3;
    static String randomElement4;
    static String randomElement5;

    String symbols[] = {"~","!","#","^","*","(",")","-","+","{","}","[","]","_","?",":"};
    String letters[] = {"@","$","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","&","%"};

    public static void main(String args[])
    {
        RandomNumbers numbers = new RandomNumbers();
        RandomText rText = new RandomText();

        String plainText = "Tharanga Nuwan Kumara Tharanga Nuwan Kumara";
        String ciphertext = "";
        String key = "AABB09182736CCDD";

        Random random = new Random();
        int randomNum1 = (int)Math.floor(Math.random() * (4 - 0 + 1) + 0);
        int randomNum2 = (int)Math.floor(Math.random() * (9 - 5 + 1) + 5);
        int randomNum3 = (int)Math.floor(Math.random() * (14 - 10 + 1) + 10);
        int randomNum4 = (int)Math.floor(Math.random() * (19 - 15 + 1) + 15);
        int randomNum5 = (int)Math.floor(Math.random() * (23 - 20 + 1) + 20);
        int randomCount=random.nextInt(10);
        int randomShift=random.nextInt(10);
        int randomStart=random.nextInt(10);

//        numbers.setRandomNum1((int)Math.floor(Math.random() * (4 - 0 + 1) + 0));
//        numbers.setRandomNum2((int)Math.floor(Math.random() * (9 - 5 + 1) + 5));
//        numbers.setRandomNum3((int)Math.floor(Math.random() * (14 - 10 + 1) + 10));
//        numbers.setRandomNum4((int)Math.floor(Math.random() * (19 - 15 + 1) + 15));
//        numbers.setRandomNum5((int)Math.floor(Math.random() * (23 - 20 + 1) + 20));
//        numbers.setRandomCount(random.nextInt(10));
//        numbers.setRandomShift(random.nextInt(10));
//        numbers.setRandomStart(random.nextInt(10));



        System.out.println("random num 1 :"+randomNum1);
        System.out.println("random num 2 :"+randomNum2);
        System.out.println("random num 3 :"+randomNum3);
        System.out.println("random num 4 :"+randomNum4);
        System.out.println("random num 5 :"+randomNum5);
        System.out.println("randomCount :"+randomCount);
        System.out.println("randomShift :"+randomShift);
        System.out.println("randomStart :"+randomStart);
        Algo algo = new Algo();
        ciphertext =  algo.encrypt(plainText,randomNum1,randomNum2,randomNum3,randomNum4,randomNum5,randomCount,randomShift,randomStart);
    }
    String encrypt(String text, int r1,int r2,int r3,int r4,int r5,int count,int shift,int start){
        Algo algo = new Algo();
        String out=algo.conAscii(text,count); //add count
        String textArray[]=algo.divided(out);
        textArray=algo.conHex(textArray);
        textArray=  algo.shifting(textArray,shift);     //shift count
        textArray=algo.removeElements(textArray,r1,r2,r3,r4,r5);     // remove 2 columns
        text=algo.arrayToString(textArray);
        text=algo.convertSymbol(text,start);// give random start number Convert to Symbol
        text=algo.compress(text);
        System.out.println(text);

        return text;
    }

    String compress(String text){
        for(int i=0;i<16;i++){
            String value = (String.valueOf(i)+String.valueOf(i));
            if(i<10){
                text= text.replace(value,symbols[i]);
            }
            else if(i==10){
                text= text.replace("aa",symbols[i]);
            }
            else if(i==11){
                text= text.replace("bb",symbols[i]);
            }
            else if(i==12){
                text= text.replace("cc",symbols[i]);
            }else if(i==13){
                text= text.replace("dd",symbols[i]);
            }
            else if(i==14){
                text= text.replace("ee",symbols[i]);
            }
            else if(i==15){
                text= text.replace("ff",symbols[i]);
            }
        }
        return text;
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

    String[] removeElements(String array[],int r1,int r2,int r3,int r4,int r5){
        int count=0;
        String []newArray= new String[19];
        for(int i=0;i<array.length;i++){
            if(i==r1){
                randomElement1=array[i];
            }else if(i==r2){
                randomElement2=array[i];
            } else if(i==r3){
                randomElement3=array[i];
            }
            else if(i==r4){
                randomElement4=array[i];
            }
            else if(i==r5){
                randomElement5=array[i];
            }else {
                newArray[count]=array[i];
                count++;
            }
        }
        System.out.println("random Element 1 : "+randomElement1);
        System.out.println("random Element 2 : "+randomElement2);
        System.out.println("random Element 3 : "+randomElement3);
        System.out.println("random Element 4 : "+randomElement4);
        System.out.println("random Element 5 : "+randomElement5);

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
        while(out.length()<384){
            out=out+"9";
        }
        return out;
    }

    public String[] divided(String str){
        int len = str.length(),n = 24,temp = 0,chars = len/n;
        String[] equalStr = new String [n];

        for(int i = 0; i < len; i = i+chars) {
            String part = str.substring(i, i+chars);
            equalStr[temp] = part;
            temp++;
        }
        return  equalStr;
    }
}