package person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void personKeepsGivenProfession() {
        Person person = new Person("Ivan", 30, "Teacher");

        assertEquals("Ivan", person.getName());
        assertEquals(30, person.getAge());
        assertEquals("Teacher", person.getProfession());
    }

    @Test
    void firemanHasDefaultProfession() {
        assertEquals("Fireman", new Fireman("Petro", 35).getProfession());
    }

    @Test
    void policemanHasDefaultProfession() {
        assertEquals("Policeman", new Policeman("Oleh", 40).getProfession());
    }
}
