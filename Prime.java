import java.util.Scanner;
import static java.lang.Math.log;
/**
Search file for TODO:
- input validation
- discard even numbers except 2

*/
public class Prime
{
	/**
	 * Main Method.
	 *
	 * @param
	 * @return
	 */
	public static void main(String []args)
	{
		Prime primeObj = new Prime();
		primeObj.coordinator();
	}
	/**
	 * Method that'll tell other methods to do some work!
	 *
	 * @param
	 * @return
	 */
	private void coordinator()
	{
		int input = getUserInput();
		System.out.println("coord:You entered: " + input);
		sieveOfEratosthenes(input);
	}
	/**
	 * Method to get user input (natural number > 0).
	 *
	 * @param
	 * @return
	 */
	 private int getUserInput()
	 { //http://stackoverflow.com/questions/5287538/how-can-i-get-the-user-input-in-java
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a positive Integer (1,2,3,...): ");
		int input = scanner.nextInt();
		// could add input validation TODO
		while(input < 1){
			System.out.println("Enter a positive Integer (1,2,3,...): ");
			input = scanner.nextInt();
		}
		System.out.println("You entered: " + input);
		return input;
	 }
	 /**
	 * Method to compute Prime numbers. 
	 * - TODO: exclude any even numbers except 2 right away
	 *
	 * @param
	 * @return
	 */
	 private void sieveOfEratosthenes(int input)
	 {
		 // Have to estimate the highest prime of the input.
		 // http://stackoverflow.com/questions/1042717/is-there-a-way-to-find-the-approximate-value-of-the-nth-prime/1069023#1069023
		 double in = (double)input;
		 double estimate = in*log(in) + in*log(log(in));
		 int estimateHighestPrime = (int)Math.ceil(estimate);
		 System.out.println("Estimate of highest Prime: " + estimateHighestPrime);
		 System.out.println("1mil and 1th prime is 15,485,867");
		 System.out.println("2mil and 1th prime is 32,452,867");
		 System.out.println("2, 3, 5, 7, 11, 13, 17, 19"); //expecting wrong estimate for low numbers
		 // When input is 3, the estimate is too low.


		 
	 }
	
}