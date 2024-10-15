package primechecker;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrimeCheckerTest {

    @Test
    public void testFindPrimes() {
        CacheManager cacheManager = new CacheManager();
        PrimeChecker checker = new PrimeChecker(cacheManager);
        List<BigInteger> primes = checker.findPrimesBigInteger("12345");
        assertEquals(4, primes.size(), "Expecting 4 primes");
        assertTrue(primes.contains(BigInteger.valueOf(2)), "List should contain prime number 2");
        assertTrue(primes.contains(BigInteger.valueOf(3)), "List should contain prime number 3");
        assertTrue(primes.contains(BigInteger.valueOf(5)), "List should contain prime number 5");
        assertTrue(primes.contains(BigInteger.valueOf(23)), "List should contain prime number 23");
    }

    @Test
    public void testNoPrimes() {
        CacheManager cacheManager = new CacheManager();
        PrimeChecker checker = new PrimeChecker(cacheManager);
        List<BigInteger> primes = checker.findPrimesBigInteger("4");
        assertTrue(primes.isEmpty(), "No primes should be found");
    }

    @Test
    public void testMaximumLimit() {
        CacheManager cacheManager = new CacheManager();
        PrimeChecker checker = new PrimeChecker(cacheManager);
        String maxInput = "12345678901234567890";
        List<BigInteger> primes = checker.findPrimesBigInteger(maxInput);
        assertNotNull(primes, "Primes list should not be null for maximum limit input");
    }

    @Test
    public void testExceedingMaxLimit() {
        CacheManager cacheManager = new CacheManager();
        PrimeChecker checker = new PrimeChecker(cacheManager);
        String exceedingInput = "1".repeat(50);
        List<BigInteger> primes = checker.findPrimesBigInteger(exceedingInput);
        assertTrue(primes.isEmpty(), "Primes list should be empty for input exceeding the limit");
    }

    @Test
    public void testFloatInput() {
        CacheManager cacheManager = new CacheManager();
        PrimeChecker checker = new PrimeChecker(cacheManager);
        List<BigInteger> primes = checker.findPrimesBigInteger("12.34");
        assertTrue(primes.isEmpty(), "Float input should not return any primes");
    }

    @Test
    public void testNegativeInput() {
        CacheManager cacheManager = new CacheManager();
        PrimeChecker checker = new PrimeChecker(cacheManager);
        List<BigInteger> primes = checker.findPrimesBigInteger("-12345");
        assertTrue(primes.isEmpty(), "Negative input should not return any primes");
    }

    @Test
    public void testEmptyInput() {
        CacheManager cacheManager = new CacheManager();
        PrimeChecker checker = new PrimeChecker(cacheManager);
        List<BigInteger> primes = checker.findPrimesBigInteger("");
        assertTrue(primes.isEmpty(), "Empty input should not return any primes");
    }

    @Test
    public void testZeroInput() {
        CacheManager cacheManager = new CacheManager();
        PrimeChecker checker = new PrimeChecker(cacheManager);
        List<BigInteger> primesZero = checker.findPrimesBigInteger("0");
        assertTrue(primesZero.isEmpty(), "Input '0' should not return any primes");
    }

    @Test
    public void testCharacterInput() {
        CacheManager cacheManager = new CacheManager();
        PrimeChecker checker = new PrimeChecker(cacheManager);
        List<BigInteger> primes = checker.findPrimesBigInteger("abc123");
        assertTrue(primes.isEmpty(), "Character input should not return any primes");
    }

    @Test
    public void testSpecialCharacterInput() {
        CacheManager cacheManager = new CacheManager();
        PrimeChecker checker = new PrimeChecker(cacheManager);
        List<BigInteger> primes = checker.findPrimesBigInteger("@#$%^&*()");
        assertTrue(primes.isEmpty(), "Special character input should not return any primes");
    }
}
