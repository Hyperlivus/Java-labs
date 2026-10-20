package turnstile.models;

import card.models.CardKind;
import card.models.PaymentResult;

import java.util.Date;

public class PaymentTransaction {
    int cardId;
    CardKind kind;
    Date createdAt;
    PaymentResult result;

    PaymentTransaction(int cardId, CardKind kind, Date createdAt, PaymentResult result) {
        this.cardId = cardId;
        this.kind = kind;
        this.createdAt = createdAt;
        this.result = result;
    }
}
