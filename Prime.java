import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import static java.lang.Math.log;
import static java.lang.Math.sqrt;
/**
Search file for TODO:
-! figure out Unit testing http://www.vogella.com/tutorials/JUnit/article.html
- input validation in function getUserInput()

General Plan:
- unit tests
- print in a pretty table (print to textfile and open it open upon completion)
- Readme file
-| prime numbers being generated (Done for now)
- make code look real pretty
- stop adding weird comments

*/
public class Prime
{
	String newLine = System.lineSeparator();// not currently used
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
		
		//System.out.println("Estimate of highest Prime: " + estimateHighestPrime);
		//System.out.println("1mil and 1th prime is 15,485,867");
		//System.out.println("2mil and 1th prime is 32,452,867");
		//System.out.println("2, 3, 5, 7, 11, 13, 17, 19"); //expecting wrong estimate for low numbers
		
		int[] primeCandidateArray = new int[(estimateHighestPrime/2)]; //only add odd numbers, so roughly half.
		int[] primeFinal = new int[input];
		// add 2 manually and disregard any even numbers after
		primeCandidateArray[0] = 2;
		int odd = 3;
		for(int i=1; i < primeCandidateArray.length; i++)
		{
			primeCandidateArray[i] = odd;
			odd = odd + 2;
		}
		// limit=sqrt(estimateHighestPrime)
		// traverse array and set non-primes to 0
		int limit = (int)Math.ceil( sqrt(estimateHighestPrime) );
		int currentPrime = 2; //imagine we just removed all even elements via the 2
		int currentPrimePosition = 0; // 0=2, 1=3, 2=5...
		System.out.print("Generating Primes... ");
		System.out.print("\nDividing by: ");
		double computingTimeStart = System.currentTimeMillis();
		while(currentPrime < limit)
		{
			//find next prime we'll divide by
			currentPrimePosition += 1;
			while(primeCandidateArray[currentPrimePosition] == 0) // not usable
			{
				currentPrimePosition += 1; // try next
			}
			currentPrime = primeCandidateArray[currentPrimePosition];
			System.out.print(currentPrime + ", ");
			// now use this prime and divide everything that follows
			for(int a = currentPrimePosition+1; a < primeCandidateArray.length; a++)
			{
				if(primeCandidateArray[a] % currentPrime == 0) //it divides, so cant be prime
				{
					primeCandidateArray[a] = 0; 
				}
			}
		}
		double computingTimeEnd = System.currentTimeMillis(); //dont know how accurate this actually is, just a plaything
		
		double printingTimeStart = System.currentTimeMillis();
		//now only primes and 0's are left in the array
		/*
		System.out.println("\nPrimeArray: ");
		int counter=0;
		for(int b = 0; b < primeCandidateArray.length; b++)
		{
			if(primeCandidateArray[b] != 0 )
			{
				System.out.print(primeCandidateArray[b] + " ");
				counter++;
			}
		}
		System.out.println("\nArray.length: " + counter); //to see how many extra primes were generated
		*/
		double printingTimeEnd = System.currentTimeMillis();
		
		printTimeTaken(computingTimeEnd, computingTimeStart, printingTimeEnd, printingTimeStart);
		
		
		//copy from candidate to final
		int j = 0;
		for(int i = 0; i < primeCandidateArray.length; i++) //candiate array has more primes than fit
		{
			if(primeCandidateArray[i] != 0)
			{	
				primeFinal[j] = primeCandidateArray[i];
				j++;
			}
			if(j >= input){ break; }
		}
		writeTableTofile(primeFinal, input);
		
	}
	/* 
	
	*/
	private void writeTableTofile(int[] primeArrayFinal, int amountToPrint)
	{
		/* 
		| [length of last Prime] | [length: 1st * last] | [etc] | [length= lastPrime*lastPrime] |
		*/
		//String[] paddingArray = new String[(amountToPrint+1)];
		// revisit printf and a paddingArray
		System.out.println("\nPrinting Primes to textfile 'PrimeMultiTable.txt' now...");
		try
		{
			PrintWriter write = new PrintWriter("PrimeMultiTable.txt", "UTF-8");
			int lastPrime = primeArrayFinal[(primeArrayFinal.length-1)];
			int lengthLastPrime = String.valueOf(lastPrime).length();
			String padding = new String(new char[lengthLastPrime]).replace("\0", " ");
			
			write.print("| " + padding + " ");
			
			long totalColLength;
			long currentNumLength;
			int paddingLength;
			for(int i = 0; i < primeArrayFinal.length; i++) 
			{		 
				totalColLength   = String.valueOf( (long)primeArrayFinal[i] * (long)lastPrime).length();
				currentNumLength = String.valueOf( (long)primeArrayFinal[i] ).length();
				paddingLength	 = (int)( totalColLength - currentNumLength );
				padding 		 = new String(new char[ paddingLength ]).replace("\0", " ");
				write.print("| " + padding + primeArrayFinal[i] + " ");
			}
			write.print("|\n");
			System.out.println("Printing " +amountToPrint+ " lines...");
			
			for(int a = 0; a < primeArrayFinal.length; a++)
			{	//if(a%100 == 0){System.out.print(a + ", ");}
				// prime numbers leftmost column
				totalColLength 	 = String.valueOf( (long)lastPrime ).length();
				
				currentNumLength = String.valueOf( (long)primeArrayFinal[a] ).length();
				paddingLength 	 = (int)( totalColLength - currentNumLength );
				padding 		 = new String(new char[ paddingLength ]).replace("\0", " ");
				// printing
				write.print("| " + padding + primeArrayFinal[a] + " ");
				
				long multiplic;
				long longestValInCol;
				for(int b = 0; b < primeArrayFinal.length; b++) //million times
				{
					// print one line starting after leftmost column has been done
					multiplic  		= (long)primeArrayFinal[a] * (long)primeArrayFinal[b];
					longestValInCol = (long)primeArrayFinal[b] * (long)lastPrime; //swapped the b for an a, so was calculating row rather than column
					// figure out padding
					totalColLength   = String.valueOf(longestValInCol).length();
					currentNumLength = String.valueOf( multiplic ).length();
					paddingLength    = (int)( totalColLength - currentNumLength );
					padding 		 = new String(new char[ paddingLength ]).replace("\0", " ");
					// printing
					write.print("| " + padding + multiplic + " ");
				}
				write.print("|\n");
			}
			
			
			write.println("\nThe end.");
			write.close();
		} 
		catch (IOException e) 
		{
			System.out.println("Failed to create textfile with Multiplication table.");
		}
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
		
		//could revisit this later
		/* 
		double printTime = printingTimeEnd - printingTimeStart;
		if(printTime > 5000)
		{
			printTime =  printTime/1000;
			System.out.println("\nTook " + printTime + " seconds to print Primes.");
		}
		else
		{
			System.out.println("\nTook " + printTime + " milliseconds to print Primes.");
		} */
	}
}