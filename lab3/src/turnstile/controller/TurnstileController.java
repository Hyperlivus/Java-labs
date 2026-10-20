package turnstile.controller;

import card.dto.PaymentDto;
import card.dto.validation.DtoValidator;
import card.models.CardKind;
import card.models.ICardModel;
import card.models.PaymentResult;
import turnstile.dto.ScanResult;
import turnstile.models.TurnstileModel;
import turnstile.views.StatisticsView;
import turnstile.views.TurnstileView;

public class TurnstileController {
    private final ICardModel cardModel;
    private final TurnstileModel model;
    private final TurnstileView view;
    private final StatisticsView statisticsView;

    public TurnstileController(
            ICardModel cardModel,
            TurnstileModel model,
            TurnstileView view,
            StatisticsView statisticsView
    ) {
        this.cardModel = cardModel;
        this.model = model;
        this.view = view;
        this.statisticsView = statisticsView;
    }

    public void processScan(ScanResult scan) {
        PaymentDto dto = new PaymentDto(scan.id());
        DtoValidator.validate(dto);

        PaymentResult result = this.cardModel.processPayment(dto);

        CardKind kind = this.cardModel.findCard(scan.id())
                .map(card -> card.kind)
                .orElse(null);

        this.model.addTransaction(scan.id(), kind, result);
        this.view.showResult(scan.id(), result);
    }

    public void showStatistics() {
        this.statisticsView.show(this.model.getStatistics());
    }
}
