package hm.domain;

import java.util.List;

public class Customer implements Identifiable<Customer> {
    private String id;
    private String firstName;
    private String lastName;
    private Cart cart;

    private Customer(String id, String firstName, String lastName, Cart cart) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cart = cart;
    }

    public static Customer create() {
        return new Customer("", "", "", Cart.create());
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Cart getCart() {
        return cart;
    }

    public List<Item> getCartItems() {
        return cart.getItems();
    }

    public int getNumberOfUnitsInCartOf(String productId) {
        return cart.getNumberOfUnitsOf(productId);
    }

    public Customer withId(String id) {
        return new Customer(id, firstName, lastName, cart);
    }

    public Customer withFirstName(String firstName) {
        return new Customer(id, firstName, lastName, cart);
    }

    public Customer withLastName(String lastName) {
        return new Customer(id, firstName, lastName, cart);
    }

    public Customer withItemInCart(String productId, double price, int numberOfUnits) {
        return new Customer(id, firstName, lastName, cart.withItem(productId, price, numberOfUnits));
    }

    public Customer withoutItemInCart(String productId) {
        return new Customer(id, firstName, lastName, cart.withoutItem(productId));
    }

    public Customer withEmptyCart() {
        return new Customer(id, firstName, lastName, Cart.create());
    }
}