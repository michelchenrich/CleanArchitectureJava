package hm;

import hm.usecases.customer.CreateCustomerResponder;
import hm.usecases.customer.Customer;
import hm.usecases.customer.PresentCustomerResponder;
import hm.usecases.customer.UpdateCustomerResponder;
import hm.usecases.product.*;

class FakeResponder implements PresentCustomerResponder, CreateCustomerResponder, UpdateCustomerResponder, CreateProductResponder, PresentProductResponder, AddProductUnitResponder, UpdateProductResponder {
    public boolean firstNameIsEmpty;
    public boolean lastNameIsEmpty;
    public boolean invalidId;
    public String createdWithId;
    public Customer customer;
    public Product product;
    public boolean nameIsEmpty;
    public boolean descriptionIsEmpty;
    public boolean pictureURIIsEmpty;
    public boolean priceIsNegative;

    public void invalidId(String id) {
        invalidId = true;
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
}
