package primechecker;

public class ValidationFactory {

    public static InputValidator getValidator(String type) {
        if ("numeric".equals(type) || "username".equals(type)) {
            return new InputValidator();
        }
        // Devs can add more validators here as needed in the future
        return null;
    }
}