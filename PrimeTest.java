import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class PrimeTest {
  @Test
  public void check3Primes() {
    Prime prime = new Prime();
	
    int[] array = prime.sieveOfEratosthenes(3);
	int[] expected = {2,3,5};
    assertArrayEquals(expected, array);
  }
}
/* 
compile
javac -cp .;junit-4.12.jar PrimeTest.java
run
java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore PrimeTest
*/