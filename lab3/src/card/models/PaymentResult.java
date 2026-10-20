package card.models;

public record PaymentResult(
        boolean ok,
        PaymentError error
) {
    static PaymentResult success() {
        return new PaymentResult(true, null);
    }

    static PaymentResult error(PaymentError error) {
        return new PaymentResult(false, error);
    }
}
