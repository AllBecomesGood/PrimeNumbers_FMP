import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;
import java.util.*;
import java.io.IOException;
import java.nio.file.*;

/* 
compile
javac -cp .;junit-4.12.jar PrimeTest.java
run
java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore PrimeTest
*/
public class PrimeTest {
  @Test
  public void check3Primes() 
  {
    Prime prime = new Prime();
	
    int[] array = prime.sieveOfEratosthenes(3);
	int[] expected = {2,3,5};
    assertArrayEquals(expected, array);
	System.out.println("Running test 'check3Primes()'...");
  }
  @Test
  public void check1000Primes()
  {
	  Prime prime = new Prime();
	  int[] result = prime.sieveOfEratosthenes(1000);
	  int[] expected = readPrimesFromTxt();
	  assertArrayEquals(expected, result);
	  System.out.println("Running test 'check1000Primes()'...");
  }
  private int[] readPrimesFromTxt()
  {
	Path filePath = Paths.get("1000Primes.txt");
  try
  {
	Scanner scanner = new Scanner(filePath);
	int[] integerArray = new int[1000];
	int i=0;
	while (scanner.hasNext()) 
	{
		if (scanner.hasNextInt()) 
		{	
			integerArray[i] = scanner.nextInt();
			i++;
		} 
		else 
		{
			scanner.next();
		}
	}
	return integerArray;
  } catch (IOException e)
  {
	  //blargh
  }
	return null;
  }
}
