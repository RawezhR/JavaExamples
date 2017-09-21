import java.util.Scanner;

/**
 *  This sample program shows how to use the Scanner class to
 *  accept input from the user.
 *  
 *  The input must match what the program asks for. Be careful to call nextLine()
 *  after calling nextInt() (for example), to advance to the next line,
 *  unless of course you expect input after the number.
 */
public class ScannerExample {

	public static void main(String[] args) {

		/** create a Scanner object to accept input. */
		Scanner in = new Scanner(System.in);

		/** ask the user for a name and advance to the next line */
		System.out.println("Enter your name: ");
		String name = in.nextLine();

		/** ask the user for an age, then advance to the next line */
		System.out.println("Enter your age: ");
		int age = in.nextInt();
		in.nextLine(); //ignore the rest of the line

		/** ask the user for three floats, then advance to the next line */
		System.out.println("Enter three numbers: ");
		float num1 = in.nextFloat();
		float num2 = in.nextFloat();
		float num3 = in.nextFloat();
		in.nextLine();

		/** ask the user for a food and advance to the next line */
		System.out.println("Enter your favorite food: ");
		String food = in.nextLine();

		/** ask the user for a number, then treat the rest of the line as the color. Advance to the next line */
		System.out.println("Enter your favorite number and color: ");
		int favNum = in.nextInt();
		String favColor = in.nextLine();

		/** output the data */
		System.out.println("\n\nHello " + name + "!");
		System.out.println("You are " + age + " years old.");
		System.out.println("The average of those three numbers is " + ((num1 + num2 + num3)/3));
		System.out.println("Your favorite food is " + food + ".");
		System.out.println("Your favorite number and color are " + favNum + " and " + favColor + ".");

		in.close();
	}

}

