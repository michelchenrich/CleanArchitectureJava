package hm.usecases.customer;

import hm.usecases.Identifiable;

public class Customer implements Identifiable<Customer> {
    private String id;
    private String firstName;
    private String lastName;

    private Customer(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Customer create() {
        return new Customer("", "", "");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return String.format("%s, %s", lastName, firstName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer withId(String id) {
        return new Customer(id, firstName, lastName);
    }

    public Customer withFirstName(String firstName) {
        return new Customer(id, firstName, lastName);
    }

    public Customer withLastName(String lastName) {
        return new Customer(id, firstName, lastName);
    }
}