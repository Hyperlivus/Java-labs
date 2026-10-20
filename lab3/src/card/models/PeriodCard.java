package card.models;

import java.time.Instant;
import java.util.Date;

public class PeriodCard extends Card {
    long period;

    public PeriodCard(
            int id,
            Date issuedAt,
            CardKind kind,
            long period
    ) {
        super(id, issuedAt, kind);
        this.period = period;
    }


    @Override
    PaymentResult processPayment() {
        Date now = Date.from(Instant.now());
        long diff = this.issuedAt.getTime() - now.getTime();

        if (diff > this.period) {
            return PaymentResult.error(PaymentError.PeriodEnded);
        }

        return PaymentResult.success();
    }
}