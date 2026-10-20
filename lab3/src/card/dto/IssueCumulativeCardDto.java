package card.dto;

import card.dto.validation.AllowedKinds;
import card.dto.validation.AllowedValues;
import card.models.CardKind;
import jakarta.validation.constraints.NotNull;

public record IssueCumulativeCardDto(
        @NotNull
        @AllowedKinds(value = {CardKind.Scholar, CardKind.Student}, message = "може бути лише Школяр або Студент")
        CardKind kind,
        @AllowedValues({5, 10}) int credit
) {
}
