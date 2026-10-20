package card.models;

import java.util.Date;

public class CumulativeCard extends Card {
    private int credit;

    public CumulativeCard(int id, Date issuedAt, CardKind kind, int credit) {
        super(id, issuedAt, kind);
        this.credit = credit;
    }


    public int getCredit() {
        return this.credit;
    }


    @Override
    PaymentResult processPayment() {
        if (this.credit == 0) {
            return PaymentResult.error(PaymentError.CreditsAreOver);
        }

        this.credit--;
        return PaymentResult.success();
    }
}
