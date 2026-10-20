package turnstile.models;

import card.models.CardKind;

public record CardStatistic(
        int cardId,
        CardKind kind,
        long granted,
        long denied
) {
}
