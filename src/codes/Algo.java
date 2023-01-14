package codes;

public class Algo {


    public static void main(String args[])
    {
        String text = "Hi how are you Nimal";
        String key = "AABB09182736CCDD";
        Algo algo = new Algo();
        algo.encrypt(text);
    }
    void encrypt( String text){

        Algo algo = new Algo();
        String out=algo.conAscii(text,5); //add count
        String textArray[]=algo.divided(out);
        textArray=algo.conHex(textArray);
        textArray=  algo.shifting(textArray,3);     //shift count

        for(int i = 0; i < textArray.length; i++) {
            System.out.println(textArray[i]);
        }
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