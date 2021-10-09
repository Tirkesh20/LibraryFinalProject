package by.emaptc.LibraryProject.entity;

public abstract class Person extends Entity {
    public String name;
    public String lastName;


    public Person(String name, String surname) {
        this.name=name;
        this.lastName =surname;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
