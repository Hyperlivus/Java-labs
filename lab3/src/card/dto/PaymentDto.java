package card.dto;

import jakarta.validation.constraints.Positive;

public record PaymentDto(
        @Positive int cardId
) {
}
