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
                "032a714bc7d76d",
                "055e12e983db98",
                "078bdc10088f98",
                "04569f0cc9eb6c",
                "0d2c6a6782b0b3",
                "11e53a49bda805",
                "03d73a77fbd9fd",
                "08906e69f467e4",
                "077f23b1986918",
                "040dd5a1c3c6ff",
                "2386f26fc0ffff",
                "2386f26fc0ffff"
        };

        AlgoDe algo = new AlgoDe();
        text = algo.hecDecimal(arr);
        String out=algo.deConAscii(text,5);// decrement assci value
        System.out.println(out);

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
            System.out.println(a);
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
//     String[] divided(String text){
//       for()
//        return
//     }
}
