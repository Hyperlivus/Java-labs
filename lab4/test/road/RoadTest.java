package road;

import org.junit.jupiter.api.Test;
import person.Fireman;
import person.Person;
import person.Policeman;
import vehicle.Bus;
import vehicle.FireEngine;
import vehicle.PoliceCar;
import vehicle.Taxi;

import static org.junit.jupiter.api.Assertions.*;

class RoadTest {

    @Test
    void newRoadIsEmpty() {
        Road road = new Road();

        assertTrue(road.vehicles.isEmpty());
        assertEquals(0, road.getHumanCount());
    }

    @Test
    void addVehicleAddsItToRoad() {
        Road road = new Road();
        Bus bus = new Bus(10);

        road.addVehicle(bus);

        assertEquals(1, road.vehicles.size());
        assertSame(bus, road.vehicles.get(0));
    }

    @Test
    void emptyVehiclesAddNoHumans() {
        Road road = new Road();

        road.addVehicle(new Bus(10));
        road.addVehicle(new Taxi(4));

        assertEquals(2, road.vehicles.size());
        assertEquals(0, road.getHumanCount());
    }

    @Test
    void humanCountForSingleVehicle() throws Exception {
        Road road = new Road();
        Bus bus = new Bus(5);
        bus.addPassenger(new Person("Ivan", 30, "Teacher"));
        bus.addPassenger(new Fireman("Petro", 35));

        road.addVehicle(bus);

        assertEquals(2, road.getHumanCount());
    }

    @Test
    void humanCountSumsPassengersOfDifferentVehicles() throws Exception {
        Road road = new Road();

        Bus bus = new Bus(10);
        bus.addPassenger(new Person("Ivan", 30, "Teacher"));
        bus.addPassenger(new Fireman("Petro", 35));
        bus.addPassenger(new Policeman("Oleh", 40));

        Taxi taxi = new Taxi(4);
        taxi.addPassenger(new Person("Maria", 25, "Doctor"));

        FireEngine fireEngine = new FireEngine(2);
        fireEngine.addPassenger(new Fireman("Andrii", 28));
        fireEngine.addPassenger(new Fireman("Serhii", 31));

        PoliceCar policeCar = new PoliceCar(2);
        policeCar.addPassenger(new Policeman("Taras", 33));

        road.addVehicle(bus);
        road.addVehicle(taxi);
        road.addVehicle(fireEngine);
        road.addVehicle(policeCar);

        assertEquals(4, road.vehicles.size());
        assertEquals(7, road.getHumanCount());
    }

    @Test
    void humanCountReflectsChangesAfterVehicleAdded() throws Exception {
        Road road = new Road();
        PoliceCar policeCar = new PoliceCar(3);
        Policeman policeman = new Policeman("Oleh", 40);
        road.addVehicle(policeCar);

        policeCar.addPassenger(policeman);
        policeCar.addPassenger(new Policeman("Taras", 33));
        assertEquals(2, road.getHumanCount());

        policeCar.removePassenger(policeman);
        assertEquals(1, road.getHumanCount());
    }
}
