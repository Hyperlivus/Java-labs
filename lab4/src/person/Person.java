package person;

import java.util.Objects;

public class Person {
    private String name;
    private int age;
    private String profession;

    public Person(String name, int age, String profession) {
        this.name = name;
        this.age = age;
        this.profession = profession;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.age, this.profession);
    }

    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public String getProfession() {
        return profession;
    }
}

