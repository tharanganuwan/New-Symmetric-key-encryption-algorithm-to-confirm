package codes;

public class AlgoDe {
    public static void main(String args[])
    {

        String text = "084104097114097110103097032078117119097110032075117109097114097032075097115117110032108107106104103102100115097119101032116121117105111112108107109106104999999999999999999999999999999999999999";

        String key = "AABB09182736CCDD";

        AlgoDe algo = new AlgoDe();
        algo.decrypt(text);


//        System.out.println("Encryption:\n");
//
//        System.out.println("\nCipher Text: " + text.toUpperCase() + "\n");

    }
    void decrypt( String text){

        String arr[]={
                "2386f26fc0ffff",
                "2386f26fc0ffff",
                "2386f26fc0ffff",
                "02bd4fcbb47ed9",
                "088a296552ebcb",
                "19512a34150b5b",
                "03e97b5f69a41f",
                "2386f26fc0ffff",
                "2386f26fc0ffff",
                "2386f26fc0ffff",
                "2386f26fc0ffff",
                "2386f26fc0ffff"
        };

        AlgoDe algo = new AlgoDe();
        arr=algo.shifting(arr,3);   //shift 3 rows
        text = algo.hecDecimal(arr);
        String out=algo.deConAscii(text,5); // decrement assci value
        System.out.println(out);

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
        String[] array= new String[64];
        for(int i=0;i<equalStr.length;i++){
            int asciiVal = Integer.parseInt(equalStr[i]);
            if(asciiVal!=999)
                fText=fText+new Character((char) ((char) asciiVal-sub));
        }
        return fText;
     }
}
