package card.dto.validation;

import card.models.CardKind;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class AllowedKindsValidator implements ConstraintValidator<AllowedKinds, CardKind> {
    private CardKind[] allowed;

    @Override
    public void initialize(AllowedKinds annotation) {
        this.allowed = annotation.value();
    }

    @Override
    public boolean isValid(CardKind value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return Arrays.asList(this.allowed).contains(value);
    }
}
