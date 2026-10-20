package vehicle;

import person.Person;

public class Bus extends Vehicle<Person> {

    public Bus(int maxPassengersCount) {
        super(maxPassengersCount);
    }
}
