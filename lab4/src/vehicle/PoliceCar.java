package vehicle;

import person.Policeman;

public class PoliceCar extends Car<Policeman>  {
    public PoliceCar(int maxPassengersCount) {
        super(maxPassengersCount);
    }
}
