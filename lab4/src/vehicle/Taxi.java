package vehicle;

import person.Person;

public class Taxi extends Car<Person> {
    public Taxi(int maxPassengersCount) {
        super(maxPassengersCount);
    }
}
