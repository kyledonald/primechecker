package primechecker;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final CacheManager cacheManager = new CacheManager();
    private final PrimeChecker primeChecker;
    private final InputValidator inputValidator;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.cacheManager.loadCache();
        LoggerUtility.logInfo("Cache loaded successfully.");
        this.primeChecker = new PrimeChecker(cacheManager);
        this.inputValidator = ValidationFactory.getValidator("username");
    }

    public void start() {
        LoggerUtility.logInfo("Prime Checker app started.");
        String username = promptForUsername();

        do {
            String input = promptForNumericSequence();
            LoggerUtility.logInfo("User " + username + " entered sequence: " + input);

            List<BigInteger> primeNumbers = primeChecker.findPrimesBigInteger(input);
            cacheManager.storePrimesBigInteger(primeNumbers);
            LoggerUtility.logDebug("Prime numbers stored in cache.");

            displayPrimeNumbers(primeNumbers);

            List<BigInteger> sortedPrimes = primeNumbers.stream()
                    .distinct()
                    .sorted()
                    .toList();
            LoggerUtility.logInfo("User: " + username + " - Primes found: " + sortedPrimes);

        } while (promptToContinue());

        LoggerUtility.logInfo("Prime Checker app ended.");
    }

    private String promptForUsername() {
        String username;
        while (true) {
            System.out.print("Enter your username: ");
            username = scanner.nextLine().trim();
            if (inputValidator != null && inputValidator.isValidUsername(username)) {
                LoggerUtility.logDebug("Username validation passed for: " + username);
                return username;
            }
            System.out.println("Invalid username! Please provide a valid username.");
            LoggerUtility.logError("Invalid username entered.");
        }
    }

    private String promptForNumericSequence() {
        String input;
        while (true) {
            System.out.print("Enter a numeric sequence (max length 20): ");
            input = scanner.nextLine().trim();
            if (inputValidator != null && inputValidator.isValid(input)) {
                LoggerUtility.logDebug("Numeric sequence validation passed for input: " + input);
                return input;
            }
            System.out.println("Invalid input! Provide a numeric sequence with no spaces, letters, and 20 or less digits.");
            LoggerUtility.logError("Invalid numeric sequence entered: " + input);
        }
    }

    private void displayPrimeNumbers(List<BigInteger> primeNumbers) {
        List<BigInteger> uniqueSortedPrimes = primeNumbers.stream()
                .distinct()
                .sorted()
                .toList();

        if (uniqueSortedPrimes.isEmpty()) {
            System.out.println("No prime numbers found in the sequence.");
            LoggerUtility.logInfo("No prime numbers found in the provided sequence.");
        } else {
            String uniquePrimes = String.join(", ", uniqueSortedPrimes.stream()
                    .map(String::valueOf)
                    .toList());
            System.out.println("Prime Numbers in sequence: " + uniquePrimes);
            LoggerUtility.logInfo("Prime numbers displayed to user: " + uniquePrimes);
        }
    }

    private boolean promptToContinue() {
        String choice;
        while (true) {
            System.out.print("Would you like to enter another sequence? (Y to continue, Q to quit): ");
            choice = scanner.nextLine().trim().toUpperCase();
            if (choice.equals("Y")) {
                return true;
            } else if (choice.equals("Q")) {
                LoggerUtility.logInfo("User chose to quit the application.");
                return false;
            }
            System.out.println("Invalid input! Please enter Y to continue or Q to quit.");
            LoggerUtility.logError("Invalid choice entered for continuation prompt.");
        }
    }
}
