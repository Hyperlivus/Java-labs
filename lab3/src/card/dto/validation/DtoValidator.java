package card.dto.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DtoValidator {
    private static final Logger hibernateLogger = Logger.getLogger("org.hibernate.validator");

    static {
        hibernateLogger.setLevel(Level.WARNING);
    }

    private static final Locale UKRAINIAN = Locale.forLanguageTag("uk");

    private static final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator(Set.of(UKRAINIAN), UKRAINIAN, false))
            .buildValidatorFactory()
            .getValidator();

    public static <T> void validate(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
