package card.views;

import card.models.Card;
import card.models.CardKind;

public class CardView {
    public void showIssued(Card card) {
        System.out.println("Картку " + card.id + " випущено: " + kindName(card.kind));
    }

    public static String kindName(CardKind kind) {
        return switch (kind) {
            case Scholar -> "Школяр";
            case Student -> "Студент";
            case Common -> "Звичайна";
        };
    }
}
