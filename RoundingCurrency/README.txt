This example shows why you should use BigDecimal instead of NumberFormat when dealing with currency. Rounding problems may occur when using NumberFormat. 

NumberFormat for currency has a default rounding mode of HALF_EVEN. This mode rounds to the nearest neighbor. But if neighbors are equidistant, then it rounds to the nearest EVEN neighbor.

With BigDecimal, you can set the rounding mode accordingly. HALF_UP is the rounding mode we were taught in school. If neighbors are equidistant, then it rounds up.

For more information, see:
https://blogs.oracle.com/CoreJavaTechTips/entry/the_need_for_bigdecimal