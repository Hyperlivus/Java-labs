package card.models;

import card.dto.IssueCumulativeCardDto;
import card.dto.IssuePeriodCardDto;
import card.dto.PaymentDto;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class CardsModel implements ICardModel {
    private HashMap<Integer, Card> cards;
    private int idCounter;

    public CardsModel() {
        this.cards = new HashMap<>();
        this.idCounter = 1;
    }

    interface CardCreator {
        Card create(int id, Date now);
    }

    private Card createCard(CardCreator creator) {
        int id = this.idCounter++;
        Date now = Date.from(Instant.now());
        Card card = creator.create(id, now);
        cards.put(card.id, card);
        return card;
    }

    public CumulativeCard issueCumulativeCard(IssueCumulativeCardDto dto) {
        return (CumulativeCard) createCard((id, now) ->
                new CumulativeCard(
                        id,
                        now,
                        dto.kind(),
                        dto.credit()
                )
        );
    }

    public PeriodCard issuePeriodCard(IssuePeriodCardDto dto) {
        return (PeriodCard) createCard((id, now) ->
            new PeriodCard(
                    id,
                    now,
                    dto.kind(),
                    dto.period()
            )
        );
    }

    @Override
    public Optional<Card> findCard(int cardId) {
        return Optional.ofNullable(this.cards.get(cardId));
    }

    @Override
    public PaymentResult processPayment(PaymentDto dto) {
        Card card = this.cards.get(dto.cardId());

        if (card == null) {
            return PaymentResult.error(PaymentError.CardNotFound);
        }

        return card.processPayment();
    }
}
