/**
 *  This Class measures and displays:
 *  - how long it takes to fill an array via a normal for loop. 
 *  - how long it takes to fill an array of half the size with only odd numbers.
 *  Result for me at 10k repetitions: Odd-only twice as fast.
 *
 * What could mess with the result?
 * - other applications
 * - not enough repetitions
 * - Could anything favor one or the other?
*/
public class OddOnlyVsEven
{
	int repetitions = 10000; // How often to perform the measuring.
	int arraySize 	= 20000000;
	
	public static void main(String []args)
	{
		OddOnlyVsEven ObjectName = new OddOnlyVsEven();
		ObjectName.coordinator();
	}
	/**
	 *  Calls other functions to do some work.
	*/
	public void coordinator()
	{
		System.out.println(" =================================================================================");
		System.out.println("==== The early bird might get the worm, but the second mouse gets the cheese!  ====");
		System.out.println(" =================================================================================");
		System.out.println("Filling odd numbers into an Array of size: " + (arraySize/2) + " " + repetitions + " times.");
		System.out.println("Filling all numbers into an Array of size: " + arraySize + " " + repetitions + " times.");
		System.out.println("Measuring commences...");
		
		// Actual functionality.
		measureArrayFillOdd();
		measureArrayFillAll();
		
		System.out.println("Well, that sure was fun! Good-bye!");
	}
	
	/**
	*  Fills an Array with only odd Prime-number-Candidates and 2.
	*/
	public void ArrayFillOdd()
	{
		int[] oddArray = new int[arraySize/2];
		oddArray[0] = 2; // Manually fill 2 - the only even Prime.
		int odd = 3;
		
		
		for(int i = 1; i < oddArray.length; i++)
		{
			oddArray[i] = odd;
			odd += 2;
		}
	}
	/**
	*  Fills an Array with all Prime-number-Candidates.
	*/
	public void ArrayFillAll()
	{
		int[] evenArray = new int[arraySize];
		
		for(int i = 2; i < evenArray.length; i++)
		{
			evenArray[i] = i;
		}
	}
	
	public void measureArrayFillOdd()
	{
		long[] measurementsArray = new long[repetitions]; 
		long startTime;
		long endTime;
		
		for (int i = 0; i < repetitions; i++)
		{
			startTime = System.nanoTime();
		
			// Now call method to test.
			ArrayFillOdd();
		
			endTime = System.nanoTime();
			measurementsArray[i] = endTime - startTime;
		}
		
		doAndShowMeasurements(measurementsArray, "Odd");
	}
	
	// Shameless duplication.
	public void measureArrayFillAll()
	{
		long[] measurementsArray = new long[repetitions]; 
		long startTime;
		long endTime;
		
		for (int i = 0; i < repetitions; i++)
		{
			startTime = System.nanoTime();
		
			// Now call method to test.
			ArrayFillAll();
		
			endTime = System.nanoTime();
			measurementsArray[i] = endTime - startTime;
		}
		
		doAndShowMeasurements(measurementsArray, "All");
	}
	
	public void doAndShowMeasurements(long[] measurementsArray, String oddOrAll)
	{
		// Array contains nanoseconds as elements.
		// Count all values into total, then calc average, then display.
		long average;
		long averageInMilliSec;
		long total = 0;
		
		for(int i = 0; i < measurementsArray.length; i++)
		{
			 total += measurementsArray[i];
		}
		
		average = total / repetitions;
		averageInMilliSec = average / 1000000;
		
		System.out.println("Average taken by '" + oddOrAll + "': " + average + " nanoseconds. Roughly: " + averageInMilliSec + " milliseconds.");
	}
	
}