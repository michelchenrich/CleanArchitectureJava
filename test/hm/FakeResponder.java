package hm;

import hm.usecases.customer.CreateCustomerResponder;
import hm.usecases.customer.Customer;
import hm.usecases.customer.PresentCustomerResponder;
import hm.usecases.customer.UpdateCustomerResponder;
import hm.usecases.product.*;
import hm.usecases.sale.AddProductToCartResponder;
import hm.usecases.sale.CartItem;
import hm.usecases.sale.PresentCustomerCartResponder;

import java.util.List;

class FakeResponder implements PresentCustomerResponder, CreateCustomerResponder, UpdateCustomerResponder, CreateProductResponder, PresentProductResponder, AddProductUnitResponder, UpdateProductResponder, AddProductToCartResponder, PresentCustomerCartResponder {
    public boolean firstNameIsEmpty;
    public boolean lastNameIsEmpty;
    public String invalidId;
    public boolean idIsInvalid;
    public String createdWithId;
    public Customer customer;
    public Product product;
    public boolean nameIsEmpty;
    public boolean descriptionIsEmpty;
    public boolean pictureURIIsEmpty;
    public boolean priceIsNegative;
    public List<CartItem> cartItems;
    public boolean numberOfUnitsIsLessThanOne;

    public void invalidId(String id) {
        idIsInvalid = true;
        invalidId = id;
    }

    public void createdWithId(String id) {
        createdWithId = id;
    }

    public void nameIsEmpty() {
        nameIsEmpty = true;
    }

    public void descriptionIsEmpty() {
        descriptionIsEmpty = true;
    }

    public void pictureURIIsEmpty() {
        pictureURIIsEmpty = true;
    }

    public void firstNameIsEmpty() {
        firstNameIsEmpty = true;
    }

    public void lastNameIsEmpty() {
        lastNameIsEmpty = true;
    }

    public void customerFound(Customer customer) {
        this.customer = customer;
    }

    public void productFound(Product product) {
        this.product = product;
    }

    public void priceIsNegative() {
        priceIsNegative = true;
    }

    public void numberOfUnitsIsLessThanOne() {
        numberOfUnitsIsLessThanOne = true;
    }

    public void cartFound(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
