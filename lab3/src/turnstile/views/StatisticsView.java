package turnstile.views;

import card.views.CardView;
import common.TableWriter;
import turnstile.models.CardStatistic;

import java.util.List;

public class StatisticsView {
    private record Row(
            int cardId,
            String kind,
            long granted,
            long denied
    ) {
    }

    public void show(List<CardStatistic> statistics) {
        long totalGranted = statistics.stream().mapToLong(CardStatistic::granted).sum();

        List<Row> rows = statistics.stream()
                .map(statistic -> new Row(
                        statistic.cardId(),
                        statistic.kind() == null ? "—" : CardView.kindName(statistic.kind()),
                        statistic.granted(),
                        statistic.denied()
                ))
                .toList();

        new TableWriter<>(rows)
                .localize("cardId", "Картка")
                .localize("kind", "Тип")
                .localize("granted", "Проходів")
                .localize("denied", "Відмов")
                .write();

        System.out.println("Всього витрачено поїздок: " + totalGranted);
    }
}
