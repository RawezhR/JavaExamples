import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;


public class RoundingCurrency {

    /**
     * @author Edwin Torres
     *  email: CoachEd@gmail.com
     *  
     *  Description: This example shows why you should use BigDecimal 
     *  instead of NumberFormat when dealing with currency. Rounding 
     *  problems may occur when using NumberFormat.  
     */
    public static void main(String[] args) {

        double amount = 4.905; //when rounding this number, we expect 4.91

        /* NumberFormat rounds amount to 4.90 */
        NumberFormat money = NumberFormat.getCurrencyInstance();
        System.out.println(money.format(amount)); //prints $4.90

        /* BigDecimal rounds amount to 4.91 */
        BigDecimal bd = new BigDecimal(amount + "");
        System.out.println("$" + bd.setScale(2, RoundingMode.HALF_UP)); //prints $4.91


        /**
         * Explanation:
         * 
         * NumberFormat for currency has a default rounding mode of
         * HALF_EVEN. This mode rounds to the nearest neighbor.
         * But if neighbors are equidistant, then it rounds to the
         * nearest EVEN neighbor.
         * 
         * With BigDecimal, you can set the rounding mode accordingly.
         * HALF_UP is the rounding mode we were taught in school. If
         * neighbors are equidistant, then it rounds up.
         *
         * For more information, see:
         * https://blogs.oracle.com/CoreJavaTechTips/entry/the_need_for_bigdecimal
         */


    }

}
