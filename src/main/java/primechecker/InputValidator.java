package primechecker;

public class InputValidator {

    private static final int MAX_LENGTH = 20;

    // Constructor to allow instantiation through the factory
    public InputValidator() {
    }

    public boolean isValid(String input) {
        return input != null && input.matches("\\d+") && input.length() <= MAX_LENGTH;
    }

    public boolean isValidUsername(String username) {
        return username != null && !username.trim().isEmpty();
    }
}