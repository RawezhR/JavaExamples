
public class FizzBuzz {

	public static void fizzbuzz(int start, int finish) {
		if (start > finish) {
			System.err.println("error - start is greater than finish");
			return;
		}
		
		for (int i=start; i <= finish; i++) {
			System.out.println(i);
		}
	}
	
	public static void main(String[] args) {
		
		fizzbuzz(0,4);
		
	}

}
