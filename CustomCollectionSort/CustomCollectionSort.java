import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * Author: Edwin Torres
 *  email: CoachEd@gmail.com
 *  
 * This class demonstrates the use of a Comparator to implement
 * a custom sort. The strings are made up of a number followed by a space
 * and text. For example: "10 miles", "30 miles", "500 miles", "100 miles"
 * The collection is sorted by the number (not the text) in ascending order.
 */
public class CustomCollectionSort implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        String[] arr = s1.split("\\s");
        int i1 = Integer.parseInt(arr[0]);

        arr = s2.split("\\s");
        int i2 = Integer.parseInt(arr[0]);

        /** return the correct comparator value */
        if(i1 < i2) {
            return -1; /* s1 < s2 */
        } else if (i1 > i2) {
            return 1; /* s1 > s2 */
        } else {
            return 0; /* s1 == s2 */
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        ArrayList<String> al = new ArrayList<String>();
        al.add("10 miles");
        al.add("100 miles");
        al.add("30 miles");
        al.add("10 miles");
        al.add("4 miles");

        CustomCollectionSort ccs = new CustomCollectionSort();
        Collections.sort(al, ccs);

        System.out.println(al);
    }

}
