package road;

import person.Person;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Vehicle<? extends Person>> vehicles;

    Road() {
        this.vehicles = new ArrayList<>();
    }

    public int getHumanCount() {
        int count = 0;

        for (Vehicle<? extends Person> vehicle : vehicles) {
            count += vehicle.getPassengersCount();
        }

        return count;
    }

    public void addVehicle(Vehicle<? extends Person> other) {
        this.vehicles.add(other);
    }
}
