package vehicle;

import person.Person;

public class Car<T extends Person> extends Vehicle<T> {
    public Car(int maxPassengersCount) {
        super(maxPassengersCount);
    }
}
