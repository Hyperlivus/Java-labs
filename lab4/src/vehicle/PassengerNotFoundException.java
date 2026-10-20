package vehicle;

public class PassengerNotFoundException extends Exception {
    public PassengerNotFoundException() {
        super("passenger not found in vehicle");
    }
}
