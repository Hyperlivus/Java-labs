package card.models;

import java.util.Date;



public abstract class Card {
    public final int id;
    public final Date issuedAt;
    public final CardKind kind;

    public Card(int id, Date issuedAt, CardKind kind) {
        this.id = id;
        this.issuedAt = issuedAt;
        this.kind = kind;
    }

    abstract PaymentResult processPayment();

}
