package hm.usecases;

import hm.usecases.customer.CreateCustomerResponder;
import hm.usecases.customer.Customer;
import hm.usecases.customer.PresentCustomerResponder;
import hm.usecases.customer.UpdateCustomerResponder;
import hm.usecases.product.*;
import hm.usecases.sale.cart.AddProductToCartResponder;
import hm.usecases.sale.cart.Item;
import hm.usecases.sale.cart.PresentCustomerCartResponder;
import hm.usecases.sale.order.SaleOrder;
import hm.usecases.sale.order.SubmitSaleOrderResponder;

import java.util.List;

class FakeResponder implements PresentCustomerResponder, CreateCustomerResponder, UpdateCustomerResponder, CreateProductResponder, PresentProductResponder, AddProductUnitResponder, UpdateProductResponder, AddProductToCartResponder, PresentCustomerCartResponder, SubmitSaleOrderResponder {
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
    public List<Item> items;
    public boolean numberOfUnitsIsLessThanOne;
    public SaleOrder order;

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

    public void cartFound(List<Item> items) {
        this.items = items;
    }

    public void submitted(SaleOrder order) {
        this.order = order;
    }
}
