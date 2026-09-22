package scheme;

public class InvalidSchemeField extends RuntimeException {
    public InvalidSchemeField(String field, String value) {
        super("Invalid field " + field + ": " + value);
    }
}
