package codes;

public class AlgoDe {
    public static void main(String args[])
    {

        String text = "084104097114097110103097032078117119097110032075117109097114097032075097115117110032108107106104103102100115097119101032116121117105111112108107109106104999999999999999999999999999999999999999";

        String key = "AABB09182736CCDD";

        AlgoDe algo = new AlgoDe();

        int randomNum1=3;
        int randomNum2=9;
        String randomElement1="02bd4fcbb0608c";
        String randomElement2="03a0c49097497f";

        algo.decrypt(text,randomNum1,randomNum2,randomElement1,randomElement2);
    }
    void decrypt(String text,int r1,int r2,String e1,String e2){

        String arr[]={
                "2386f26fc0ffff",
                "2386f26fc0ffff",
                "2386f26fc0ffff",
                "0d2f994fbb3aab",
                "19429dd31f7c55",
                "03a0c490104369",
                "00b945899653ba",
                "07839d71d8f302",
                "2386f26fc0ffff",
                "2386f26fc0ffff"
        };

        AlgoDe algo = new AlgoDe();
        arr=algo.addRemoveElements(arr,r1,r2,e1,e2);
        arr=algo.shifting(arr,3);   //shift 3 rows
        text = algo.hecDecimal(arr);
        String out=algo.deConAscii(text,5); // decrement assci value
        System.out.println(out);

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
        String[] array= new String[64];
        for(int i=0;i<equalStr.length;i++){
            int asciiVal = Integer.parseInt(equalStr[i]);
            if(asciiVal!=999)
                fText=fText+new Character((char) ((char) asciiVal-sub));
        }
        return fText;
     }
}
