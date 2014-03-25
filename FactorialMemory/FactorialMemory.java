import java.util.HashMap;

/**
 * @author Edwin Torres
 *   email CoachEd@gmail.com
 *   
 *   Description: This is a smart factorial class. It computes
 *   the factorial recursively and saves values along the way.
 *   If an existing factorial was already computed, it returns
 *   that value, instead of computing it again.
 */

public class FactorialMemory {

    /*
     * This HashMap stores known factorial values
     */
    private static HashMap<Integer,Long> hmAnswers;

    public FactorialMemory() {

        //create the initial HashMap
        hmAnswers = new HashMap<Integer,Long>();
    }

    /*
     * This is the recursive method to compute the factorial.
     * It retrieves already learned factorial values and
     * stores new ones.
     */
    public long factorialMem(int i) {
        if (i == 1)
            return 1;
        else {
            long val;
            if (hmAnswers.containsKey(i-1)) {
                val = hmAnswers.get(i-1);
            } else {
                val = factorialMem(i-1);
                hmAnswers.put(i-1, val);
            }

            return i * val;
        }
    }

    /*
     * This is the driver method to compute the factorial.
     * It retrieves the factorial value if it exists and
     * computes/stores the factorial value if it doesn't.
     */
    public long doFactorial(int i) {
        long val;
        if (hmAnswers.containsKey(i)) {
            return hmAnswers.get(i);
        } else {
            val = factorialMem(i);
            hmAnswers.put(i, val);
            return val;
        }
    }

    public static void main(String[] args) {

        FactorialMemory fm = new FactorialMemory();
        System.out.println(fm.doFactorial(4));
        System.out.println(fm.doFactorial(10));
        System.out.println(fm.doFactorial(8));

    }

}
