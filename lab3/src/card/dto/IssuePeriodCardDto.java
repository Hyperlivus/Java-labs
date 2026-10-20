package card.dto;

import card.models.CardKind;
import card.dto.validation.AllowedValues;
import jakarta.validation.constraints.NotNull;

public record IssuePeriodCardDto(
        @NotNull CardKind kind,
        @AllowedValues(value = {TEN_DAYS, ONE_MONTH}, message = "має бути 10 днів або 1 місяць") long period
) {
    public static final long TEN_DAYS = 10L * 24 * 60 * 60 * 1000;
    public static final long ONE_MONTH = 30L * 24 * 60 * 60 * 1000;
}
