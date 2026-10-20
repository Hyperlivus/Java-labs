package card.controller;

import card.dto.IssueCumulativeCardDto;
import card.dto.IssuePeriodCardDto;
import card.dto.validation.DtoValidator;
import card.models.CardsModel;
import card.models.CumulativeCard;
import card.models.PeriodCard;
import card.views.CardView;

public class CardController {
    private final CardsModel model;
    private final CardView view;

    public CardController(CardsModel model, CardView view) {
        this.model = model;
        this.view = view;
    }

    public CumulativeCard issueCumulativeCard(IssueCumulativeCardDto dto) {
        DtoValidator.validate(dto);
        CumulativeCard card = this.model.issueCumulativeCard(dto);
        this.view.showIssued(card);
        return card;
    }

    public PeriodCard issuePeriodCard(IssuePeriodCardDto dto) {
        DtoValidator.validate(dto);
        PeriodCard card = this.model.issuePeriodCard(dto);
        this.view.showIssued(card);
        return card;
    }
}
