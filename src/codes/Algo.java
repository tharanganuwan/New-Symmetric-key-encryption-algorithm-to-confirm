package codes;

import objects.RandomNumbers;
import objects.RandomText;
import java.util.Random;

public class Algo {
    static RandomText randomText = new RandomText();

    String symbols[] = {"~","!","#","^","*","(",")","-","+","{","}","[","]","_","?",":"};
    String letters[] = {"@","$","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","&","%"};

    public static void main(String args[])
    {
        String plainText = "Tharanga Nuwan Kumara Tharanga Nuwan Kumara Hi How are You";

        Algo algo = new Algo();
        RandomNumbers randomNumbers = algo.getRandomNumbers();
        String ciphertext =  algo.encrypt(plainText,randomNumbers);
        String key = algo.generateKey(randomNumbers);
        System.out.println("Key: "+ key);
        System.out.println("ciphertext : "+ciphertext);
    }

    String generateKey(RandomNumbers numbers){
        return randomText.getRandomElement1()+
                String.valueOf(numbers.getRandomNum1())+
                randomText.getRandomElement2()+
                randomText.getRandomElement3()+
                String.valueOf(numbers.getRandomNum2())+
                randomText.getRandomElement4()+
                String.valueOf(numbers.getRandomNum3())+
                String.valueOf(numbers.getRandomNum4())+
                String.valueOf(numbers.getRandomNum5())+
                randomText.getRandomElement5()+
                String.valueOf(numbers.getRandomCount())+
                String.valueOf(numbers.getRandomShift())+
                String.valueOf(numbers.getRandomStart());
    }

    RandomNumbers getRandomNumbers(){
        RandomNumbers numbers = new RandomNumbers();
        Random random = new Random();
        numbers.setRandomNum1((int)Math.floor(Math.random() * (4 - 0 + 1) + 0));
        numbers.setRandomNum2((int)Math.floor(Math.random() * (9 - 5 + 1) + 5));
        numbers.setRandomNum3((int)Math.floor(Math.random() * (14 - 10 + 1) + 10));
        numbers.setRandomNum4((int)Math.floor(Math.random() * (19 - 15 + 1) + 15));
        numbers.setRandomNum5((int)Math.floor(Math.random() * (23 - 20 + 1) + 20));
        numbers.setRandomCount(random.nextInt(10));
        numbers.setRandomShift(random.nextInt(10));
        numbers.setRandomStart(random.nextInt(10));
        return numbers;
    }

    String encrypt(String text, RandomNumbers numbers){
        Algo algo = new Algo();
        String out=algo.conAscii(text, numbers.getRandomCount()); //add count
        String textArray[]=algo.divided(out);
        textArray=algo.conHex(textArray);
        textArray=  algo.shifting(textArray,numbers.getRandomShift());     //shift count
        textArray=algo.removeElements(textArray,numbers);     // remove 2 columns
        text=algo.arrayToString(textArray);
        text=algo.convertSymbol(text,numbers.getRandomStart());// give random start number Convert to Symbol
        text=algo.compress(text);
        return text;
    }

    String compress(String text){
        for(int i=0;i<16;i++){
            String value = (String.valueOf(i)+String.valueOf(i));
            if(i<10){
                text= text.replace(value,symbols[i]);
            }else if(i==10){
                text= text.replace("aa",symbols[i]);
            }else if(i==11){
                text= text.replace("bb",symbols[i]);
            }else if(i==12){
                text= text.replace("cc",symbols[i]);
            }else if(i==13){
                text= text.replace("dd",symbols[i]);
            }else if(i==14){
                text= text.replace("ee",symbols[i]);
            }else if(i==15){
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
            }else{
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

    String[] removeElements(String array[],RandomNumbers numbers){
        int count=0;
        String []newArray= new String[19];
        for(int i=0;i<array.length;i++){
            if(i==numbers.getRandomNum1()){
                randomText.setRandomElement1(array[i]);
            }else if(i==numbers.getRandomNum2()){
                randomText.setRandomElement2(array[i]);
            }else if(i==numbers.getRandomNum3()){
                randomText.setRandomElement3(array[i]);
            }else if(i==numbers.getRandomNum4()){
                randomText.setRandomElement4(array[i]);
            }else if(i==numbers.getRandomNum5()){
                randomText.setRandomElement5(array[i]);
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
        return equalStr;
    }
}