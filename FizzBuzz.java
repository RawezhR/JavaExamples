/**
 * This is a solution to the classic FizzBuzz problem.
 * For numbers 1 to n, print "Fizz" if the number is divisible by 3,
 * "Buzz" if divisible by 5, and "FizzBuzz" if divisible by both.
 */
public class FizzBuzz {

	public static void fizzbuzz(int start, int finish) {
		if (start > finish) {
			System.err.println("error - start is greater than finish");
			return;
		}

		boolean bFizz, bBuzz;
		for (int i=start; i <= finish; i++) {
			bFizz = (i % 3) == 0;
			bBuzz = (i % 5) == 0;
			if (!bFizz && !bBuzz)
				System.out.println(i);
			else {
				if (bFizz) System.out.print("Fizz");
				if (bBuzz) System.out.print("Buzz");
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		fizzbuzz(1,100);
	}
}
