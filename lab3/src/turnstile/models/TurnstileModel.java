package turnstile.models;

import card.models.CardKind;
import card.models.PaymentResult;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TurnstileModel {
    private ArrayList<PaymentTransaction> history;

    public TurnstileModel() {
        this.history = new ArrayList<>();
    }

    public void addTransaction(int cardId, CardKind kind, PaymentResult result) {
        Date now = Date.from(Instant.now());
        this.history.add(new PaymentTransaction(cardId, kind, now, result));
    }

    public List<PaymentTransaction> getHistory() {
        return Collections.unmodifiableList(this.history);
    }

    public List<CardStatistic> getStatistics() {
        Map<Integer, CardStatistic> statistics = new TreeMap<>();

        for (PaymentTransaction transaction : this.history) {
            boolean ok = transaction.result.ok();
            CardStatistic current = new CardStatistic(
                    transaction.cardId,
                    transaction.kind,
                    ok ? 1 : 0,
                    ok ? 0 : 1
            );

            statistics.merge(transaction.cardId, current, (a, b) -> new CardStatistic(
                    a.cardId(),
                    a.kind(),
                    a.granted() + b.granted(),
                    a.denied() + b.denied()
            ));
        }

        return List.copyOf(statistics.values());
    }
}
