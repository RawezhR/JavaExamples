/*
 * Author: Edwin Torres
 *  email: CoachEd@gmail.com
 *  
 *  Description:
 *  
 *  You are given two arrays. The first array has unique numbers from 1 to 10. The
 *  second array has unique numbers from 1 to 10, plus an additional duplicate number.
 *  Write a method to find the duplicate number in the second array.
 */


public class DuplicateNumber {

    public static void main(String[] args) {
        
        //given two arrays
        int[] arr1 = new int[]{3,2,5,7,1,9,10,8,4,6};
        int[] arr2 = new int[]{3,2,5,7,1,9,10,8,4,6, 10 /*duplicate*/};
        
        findDuplicate(arr1,arr2);
    }

    public static void findDuplicate(int[] a, int[] adup) {
        if (a == null || adup == null) {
            System.err.println("error - one array is null");
            return;
        }
        
        int sum1 = (a.length * (a.length+1))/2; //math formula to sum the numbers from 1 to n
        int sum2 = 0;
        
        /** add up the numbers in the second array */
        for (int i=0; i < adup.length; i++) {
            sum2 += adup[i];
        }
        
        /** subtract the first sum from the second sum to get the duplicate number */
        System.out.println("The duplicate number is " + (sum2 - sum1));
    }
    
}
