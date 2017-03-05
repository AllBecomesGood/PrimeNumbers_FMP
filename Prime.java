import java.util.Scanner;
import static java.lang.Math.log;
import static java.lang.Math.sqrt;
/**
Search file for TODO:
-! figure out Unit testing http://www.vogella.com/tutorials/JUnit/article.html
- input validation in function getUserInput()

General Plan:
- unit tests
- prime numbers being generated (Done for now)
- print in a pretty table (print to textfile and opo it open upon completion)
- make code look real pretty
- stop adding weird comments
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
	{
		Scanner scanner = new Scanner(System.in);
		int input = 0;
		while(input < 1)
		{
			System.out.println("Enter a positive Integer (1,2,3,...): ");
			input = scanner.nextInt();
		}
		System.out.println("You entered: " + input);
		return input;
	}
	/**
	 * Method to compute Prime numbers. 
	 * TODO Refactor this it looks like crap
	 * @param
	 * @return
	 */
	private void sieveOfEratosthenes(int input)
	{
		// Have to estimate the highest prime of the input.
		int estimateHighestPrime;
		if(input < 7)
		{
			estimateHighestPrime = 14; // Low input causes an underestimate.
		}
		else
		{
			// http://stackoverflow.com/questions/1042717/is-there-a-way-to-find-the-approximate-value-of-the-nth-prime/1069023#1069023
			double in = (double)input;
			double estimate = in*log(in) + in*log(log(in));
			estimateHighestPrime = (int)Math.ceil(estimate);
		}
		
		System.out.println("Estimate of highest Prime: " + estimateHighestPrime);
		System.out.println("1mil and 1th prime is 15,485,867");
		System.out.println("2mil and 1th prime is 32,452,867");
		//System.out.println("2, 3, 5, 7, 11, 13, 17, 19"); //expecting wrong estimate for low numbers
		
		int[] primeArray = new int[(estimateHighestPrime/2)]; //only add odd numbers, so roughly half.
		// add 2 manually and disregard any even numbers after
		primeArray[0] = 2;
		int odd = 3;
		for(int i=1; i < primeArray.length; i++)
		{
			primeArray[i] = odd;
			odd = odd + 2;
		}
		// limit=sqrt(estimateHighestPrime)
		// traverse array and set non-primes to 0
		int limit = (int)Math.ceil( sqrt(estimateHighestPrime) );
		int currentPrime = 2; //imagine we just removed all even elements via the 2
		int currentPrimePosition = 0; // 0=2, 1=3, 2=5...
		System.out.print("Generating Primes: ");
		double computingTimeStart = System.currentTimeMillis();
		while(currentPrime < limit)
		{
			//find next prime we'll divide by
			currentPrimePosition += 1;
			while(primeArray[currentPrimePosition] == 0) // not usable
			{
				currentPrimePosition += 1; // try next
			}
			currentPrime = primeArray[currentPrimePosition];
			System.out.print(currentPrime + " ");
			// now use this prime and divide everything that follows
			for(int a = currentPrimePosition+1; a < primeArray.length; a++)
			{
				if(primeArray[a] % currentPrime == 0) //it divides, so cant be prime
				{
					primeArray[a] = 0; 
				}
			}
		}
		double computingTimeEnd = System.currentTimeMillis(); //dont know how accurate this actually is, just a plaything
		
		System.out.println("Printing Primes now...");
		double printingTimeStart = System.currentTimeMillis();
		//now only primes and 0's are left in the array
		System.out.println("\nPrimeArray: ");
		int counter=0;
		for(int b = 0; b < primeArray.length; b++)
		{
			if(primeArray[b] != 0 )
			{
				System.out.print(primeArray[b] + " ");
				counter++;
			}
		}
		double printingTimeEnd = System.currentTimeMillis();
		System.out.println("\nArray.length: " + counter);
		
		printTimeTaken(computingTimeEnd, computingTimeStart, printingTimeEnd, printingTimeStart);
		
		


		 
	}
	/*	Method to print time taken. 
	
	*/
	private void printTimeTaken(double computingTimeEnd, double computingTimeStart, double printingTimeEnd, double printingTimeStart)
	{
		double computeTime = computingTimeEnd - computingTimeStart;
		if(computeTime > 5000)
		{
			computeTime =  computeTime/1000;
			System.out.println("\nTook " + computeTime + " seconds to generate Primes.");
		}
		else
		{
			System.out.println("\nTook " + computeTime + " milliseconds to generate Primes.");
		}
		
		double printTime = printingTimeEnd - printingTimeStart;
		if(printTime > 5000)
		{
			printTime =  printTime/1000;
			System.out.println("\nTook " + printTime + " seconds to print Primes.");
		}
		else
		{
			System.out.println("\nTook " + printTime + " milliseconds to print Primes.");
		}
	}
}