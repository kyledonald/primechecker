package primechecker;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to check for prime numbers within substrings of the user input.
 */
public class PrimeChecker {
    private final CacheManager cacheManager;

    public PrimeChecker(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * Finds all prime numbers within the input numeric string.
     */
    public List<BigInteger> findPrimesBigInteger(String input) {
        List<BigInteger> primes = new ArrayList<>();

        InputValidator validator = ValidationFactory.getValidator("numeric");
        if (validator != null && !validator.isValid(input)) {
            System.out.println("Invalid input: " + input);
            return primes;
        }

        if (input.startsWith("-")) {
            return primes;
        }

        // extracts all possible substrings from the input
        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j <= input.length(); j++) {
                String substring = input.substring(i, j);
                if (substring.isEmpty()) continue;

                try {
                    BigInteger number = new BigInteger(substring);

                    if (cacheManager.isPrimeInCache(number)) {
                        LoggerUtility.logInfo("Found " + number + " in the cache.");
                        primes.add(number);
                    } else if (isPrimeBigInteger(number)) {
                        primes.add(number);
                        cacheManager.storePrimesBigInteger(List.of(number));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number: " + substring);
                }
            }
        }
        return primes;
    }

    /**
     * checks if it's prime
     */
    private boolean isPrimeBigInteger(BigInteger number) {
        if (number.compareTo(BigInteger.ONE) <= 0) return false;
        BigInteger sqrt = number.sqrt(); // only checks up to the square root of the number
        for (BigInteger i = BigInteger.TWO; i.compareTo(sqrt) <= 0; i = i.add(BigInteger.ONE)) {
            if (number.mod(i).equals(BigInteger.ZERO)) {
                return false;
            }
        }
        return true;
    }
}
