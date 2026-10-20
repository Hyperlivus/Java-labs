package card.dto.validation;

import card.models.CardKind;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllowedKindsValidator.class)
public @interface AllowedKinds {
    CardKind[] value();

    String message() default "має бути одним із {value}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
