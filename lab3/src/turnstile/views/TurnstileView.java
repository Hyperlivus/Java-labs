package turnstile.views;

import card.models.PaymentResult;

public class TurnstileView {
    public void showResult(int cardId, PaymentResult result) {
        System.out.println(toMessage(cardId, result));
    }

    private String toMessage(int cardId, PaymentResult result) {
        if (result.ok()) {
            return "Картка " + cardId + ": прохід дозволено";
        }

        String reason = switch (result.error()) {
            case CardNotFound -> "картку не знайдено";
            case CreditsAreOver -> "поїздки закінчилися";
            case PeriodEnded -> "термін дії картки минув";
        };

        return "Картка " + cardId + ": прохід заборонено, " + reason;
    }
}
