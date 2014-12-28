package hm.usecases.customer;

import hm.usecases.Identifiable;
import hm.usecases.product.Product;
import hm.usecases.sale.Cart;
import hm.usecases.sale.Item;

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

    public String getName() {
        return String.format("%s, %s", lastName, firstName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Item> getCartItems() {
        return cart.getItems();
    }

    public int getNumberOfUnitsInCartOf(Product product) {
        return cart.getNumberOfUnitsOf(product);
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

    public Customer withNewItemInCart(Product product, int numberOfUnits) {
        return new Customer(id, firstName, lastName, cart.withItem(product, numberOfUnits));
    }

    public Customer withoutItemInCart(Product product) {
        return new Customer(id, firstName, lastName, cart.withoutItem(product));
    }

    public Customer withEmptyCart() {
        return new Customer(id, firstName, lastName, Cart.create());
    }
}