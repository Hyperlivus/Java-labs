package vehicle;

public class VehicleOverfilledException extends Exception {
    public VehicleOverfilledException() {
        super("vehicle overfilled");
    }
}
