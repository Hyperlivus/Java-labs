import card.controller.CardController;
import card.dto.IssueCumulativeCardDto;
import card.dto.IssuePeriodCardDto;
import card.models.CardKind;
import card.models.CardsModel;
import card.views.CardView;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import turnstile.controller.TurnstileController;
import turnstile.dto.ScanResult;
import turnstile.models.TurnstileModel;
import turnstile.views.StatisticsView;
import turnstile.views.TurnstileView;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CardsModel cardsModel = new CardsModel();
        CardController cardController = new CardController(cardsModel, new CardView());
        TurnstileController turnstileController = new TurnstileController(
                cardsModel,
                new TurnstileModel(),
                new TurnstileView(),
                new StatisticsView()
        );

        while (true) {
            System.out.println();
            System.out.println("1. Випустити картку на поїздки");
            System.out.println("2. Випустити картку на період");
            System.out.println("3. Пройти через турнікет");
            System.out.println("4. Статистика");
            System.out.println("0. Вихід");

            try {
                switch (readInt("> ")) {
                    case 1 -> cardController.issueCumulativeCard(
                            new IssueCumulativeCardDto(readKind(), readInt("Кількість поїздок (5 або 10): "))
                    );
                    case 2 -> cardController.issuePeriodCard(
                            new IssuePeriodCardDto(readKind(), readPeriod())
                    );
                    case 3 -> turnstileController.processScan(new ScanResult(readInt("ID картки: ")));
                    case 4 -> turnstileController.showStatistics();
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Немає такого пункту");
                }
            } catch (ConstraintViolationException e) {
                for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
                    String field = fieldName(violation.getPropertyPath().toString());
                    System.out.println("Помилка: " + field + " " + violation.getMessage());
                }
            }
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                System.exit(0);
            }

            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Введіть число");
            }
        }
    }

    private static CardKind readKind() {
        CardKind[] kinds = CardKind.values();
        for (int i = 0; i < kinds.length; i++) {
            System.out.println((i + 1) + ". " + CardView.kindName(kinds[i]));
        }

        int choice = readInt("Тип картки: ");
        return choice >= 1 && choice <= kinds.length ? kinds[choice - 1] : null;
    }

    private static long readPeriod() {
        System.out.println("1. 10 днів");
        System.out.println("2. 1 місяць");

        return switch (readInt("Період: ")) {
            case 1 -> IssuePeriodCardDto.TEN_DAYS;
            case 2 -> IssuePeriodCardDto.ONE_MONTH;
            default -> 0;
        };
    }

    private static String fieldName(String property) {
        return switch (property) {
            case "credit" -> "кількість поїздок";
            case "kind" -> "тип картки";
            case "period" -> "період";
            case "cardId" -> "ID картки";
            default -> property;
        };
    }
}
