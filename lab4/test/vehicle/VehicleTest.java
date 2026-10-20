package vehicle;

import org.junit.jupiter.api.Test;
import person.Fireman;
import person.Person;
import person.Policeman;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void newVehicleIsEmpty() {
        Bus bus = new Bus(10);

        assertEquals(0, bus.getPassengersCount());
        assertEquals(10, bus.getMaxPassengersCount());
    }

    @Test
    void busAcceptsAnyPersonType() throws Exception {
        Bus bus = new Bus(3);

        bus.addPassenger(new Person("Ivan", 30, "Teacher"));
        bus.addPassenger(new Fireman("Petro", 35));
        bus.addPassenger(new Policeman("Oleh", 40));

        assertEquals(3, bus.getPassengersCount());
    }

    @Test
    void taxiAcceptsAnyPersonType() throws Exception {
        Taxi taxi = new Taxi(4);

        taxi.addPassenger(new Person("Ivan", 30, "Teacher"));
        taxi.addPassenger(new Fireman("Petro", 35));
        taxi.addPassenger(new Policeman("Oleh", 40));

        assertEquals(3, taxi.getPassengersCount());
    }

    @Test
    void fireEngineAcceptsFiremen() throws Exception {
        FireEngine fireEngine = new FireEngine(2);

        fireEngine.addPassenger(new Fireman("Petro", 35));
        fireEngine.addPassenger(new Fireman("Andrii", 28));

        assertEquals(2, fireEngine.getPassengersCount());
    }

    @Test
    void policeCarAcceptsPolicemen() throws Exception {
        PoliceCar policeCar = new PoliceCar(2);

        policeCar.addPassenger(new Policeman("Oleh", 40));
        policeCar.addPassenger(new Policeman("Taras", 33));

        assertEquals(2, policeCar.getPassengersCount());
    }

    @Test
    void carOfPersonAcceptsMixedPassengers() throws Exception {
        Car<Person> car = new Car<>(3);

        car.addPassenger(new Person("Ivan", 30, "Teacher"));
        car.addPassenger(new Fireman("Petro", 35));
        car.addPassenger(new Policeman("Oleh", 40));

        assertEquals(3, car.getPassengersCount());
    }

    @Test
    void addingPassengerToFullVehicleThrows() throws Exception {
        PoliceCar policeCar = new PoliceCar(1);
        policeCar.addPassenger(new Policeman("Oleh", 40));

        assertThrows(VehicleOverfilledException.class, () -> policeCar.addPassenger(new Policeman("Taras", 33)));
        assertEquals(1, policeCar.getPassengersCount());
    }

    @Test
    void vehicleWithZeroSeatsRejectsPassengers() {
        Bus bus = new Bus(0);

        assertThrows(VehicleOverfilledException.class, () -> bus.addPassenger(new Person("Ivan", 30, "Teacher")));
        assertEquals(0, bus.getPassengersCount());
    }

    @Test
    void removePassengerFreesSeat() throws Exception {
        FireEngine fireEngine = new FireEngine(1);
        Fireman fireman = new Fireman("Petro", 35);
        fireEngine.addPassenger(fireman);

        fireEngine.removePassenger(fireman);
        assertEquals(0, fireEngine.getPassengersCount());

        fireEngine.addPassenger(new Fireman("Andrii", 28));
        assertEquals(1, fireEngine.getPassengersCount());
    }

    @Test
    void removePassengerNotInVehicleThrows() throws Exception {
        Bus bus = new Bus(2);
        bus.addPassenger(new Person("Ivan", 30, "Teacher"));

        assertThrows(PassengerNotFoundException.class, () -> bus.removePassenger(new Fireman("Petro", 35)));
        assertEquals(1, bus.getPassengersCount());
    }

    @Test
    void removeNullPassengerThrows() {
        Taxi taxi = new Taxi(2);

        assertThrows(PassengerNotFoundException.class, () -> taxi.removePassenger(null));
    }

    @Test
    void addingSamePassengerTwiceCountsOnce() throws Exception {
        Bus bus = new Bus(2);
        Policeman policeman = new Policeman("Oleh", 40);

        bus.addPassenger(policeman);
        bus.addPassenger(policeman);

        assertEquals(1, bus.getPassengersCount());
    }
}
