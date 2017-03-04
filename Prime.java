import java.util.Scanner;

/**
Search file for TODO:
- input validation

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
	
}