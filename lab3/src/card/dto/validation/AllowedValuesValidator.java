package card.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class AllowedValuesValidator implements ConstraintValidator<AllowedValues, Number> {
    private long[] allowed;

    @Override
    public void initialize(AllowedValues annotation) {
        this.allowed = annotation.value();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return Arrays.stream(this.allowed).anyMatch(v -> v == value.longValue());
    }
}
