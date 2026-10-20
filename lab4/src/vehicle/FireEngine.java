package vehicle;

import person.Fireman;

public class FireEngine extends Car<Fireman> {
    public FireEngine(int maxPassengersCount) {
        super(maxPassengersCount);
    }
}
