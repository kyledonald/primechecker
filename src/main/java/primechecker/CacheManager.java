package primechecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Manages the cache of prime numbers.
 * Handles loading from the file to cache on start, writing to the file, and managing unique/dupe primes.
 */
public class CacheManager {
    private final List<BigInteger> cache = new ArrayList<>();
    private final String fileName = "primes.txt";

    public void storePrimesBigInteger(List<BigInteger> primes) {
        Set<BigInteger> uniquePrimes = new HashSet<>(primes);
        uniquePrimes.addAll(cache);

        List<BigInteger> sortedPrimes = new ArrayList<>(uniquePrimes);
        sortedPrimes.sort(BigInteger::compareTo);

        cache.clear();
        cache.addAll(sortedPrimes);
        writePrimesToFile(sortedPrimes);
    }

    public void loadCache() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cache.add(new BigInteger(line.trim()));
            }
        } catch (IOException e) {
            LoggerUtility.logError("Error reading existing primes from file: " + e.getMessage());
            System.out.println("Unable to fetch primes file. This is expected behaviour for your first time running the program.");
        }
        System.out.println("Cache loaded with " + cache.size() + " prime numbers from the file.");
    }

    public boolean isPrimeInCache(BigInteger number) {
        return cache.contains(number);
    }

    private void writePrimesToFile(List<BigInteger> primes) {
        try (FileWriter writer = new FileWriter(fileName, false)) { // Overwrite mode
            for (BigInteger prime : primes) {
                writer.write(prime.toString() + "\n");
            }
        } catch (IOException e) {
            LoggerUtility.logError("Error writing primes to file: " + e.getMessage());
        }
    }
}
