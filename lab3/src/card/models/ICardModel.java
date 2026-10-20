package card.models;

import card.dto.PaymentDto;

import java.util.Optional;

public interface ICardModel {
    PaymentResult processPayment(PaymentDto dto);

    Optional<Card> findCard(int cardId);
}
