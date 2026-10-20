package vehicle;

import person.Person;

import java.util.HashSet;

public abstract class Vehicle<T extends Person> {
    private HashSet<T> passengers;
    private int maxPassengersCount;

    protected Vehicle(int maxPassengersCount) {
        this.maxPassengersCount = maxPassengersCount;
        this.passengers = new HashSet<>();
    }

    public void addPassenger(T person) throws Exception {
        if (this.passengers.size() + 1 > this.maxPassengersCount) {
            throw new Exception("vehicle overfilled");
        }

        this.passengers.add(person);
    }
    public void removePassenger(T person) throws Exception {
        if (person == null || !this.passengers.contains(person)) {
            throw new Exception();
        }

        this.passengers.remove(person);
    }

    public int getMaxPassengersCount() {
        return this.maxPassengersCount;
    }
    public int getPassengersCount() {
        return this.passengers.size();
    }
}

