
/*
 * Author: Edwin Torres
 *  email: CoachEd@gmail.com
 * 
 * This program is in response to an actual programming
 * interview question. Given an array of integers, 
 * return a new array whose elements are the product of
 * every other integer except the one at the current index.
 * For example, given an input array: [4,2,3,1], the corresponding
 * output array would be: [2*3*1,4*3*1,4*2*1,4*2*3] or [6,12,8,24].
 * 
 * One algorithm to solve this is to compute the full product, 
 * walk the array, and divide the full product by the current element.
 */
public class ArrayMultiplyQuestion {

    public static void main(String[] args) {
        int[] arr = new int[]{4,2,3,1};
        int[] newarr = new int[arr.length];
        
        /** compute the full product */
        int product = 1;
        for (int i=0; i < arr.length; i++) {
            product = product * arr[i];
        }
        
        /** walk the original array and divide the full product
         *  along the way.
         */
        for (int i=0; i < arr.length; i++) {
            newarr[i] = product / arr[i];
            System.out.print(newarr[i] + " ");
        }

    }

}
