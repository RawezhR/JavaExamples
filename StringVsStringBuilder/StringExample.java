/*
 * This example shows that StringBuilder may be faster than String in 
 * certain circumstances. Since you cannot change a String object, you 
 * should consider using StringBuilder in cases when you want to change
 * the String.
 * 
 * The sample below changes the "helloX" to "hello!". The first method uses String.
 * The second method uses StringBuilder. This operation is repeated millions of times
 * and the elapsed time is displayed. Note how much faster StringBuilder is.
 */
public class StringExample {

    public static void main(String[] args) {

        String s = "helloX";
        StringBuilder sb = new StringBuilder("helloX");
        
        long start, end;
        int reps = 50000000;
        
        /** use String to change the character X to the character ! */
        start = System.currentTimeMillis();
        for (int i=0; i < reps; i++) {
            s = s.substring(0,5) + "!";
        }
        end = System.currentTimeMillis();
        System.out.println("With String, the elapsed time in seconds is " + ((float)(end-start)/1000));
        
        /** use StringBuilder to change the character X to the character ! */
        start = System.currentTimeMillis();
        for (int i=0; i < reps; i++) {
            sb.setCharAt(5, '!');
        }
        end = System.currentTimeMillis();
        System.out.println("With StringBuilder, the elapsed time in seconds is " + ((float)(end-start)/1000));
    }

}
