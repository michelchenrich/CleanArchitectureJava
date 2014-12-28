package hm.usecases;

import hm.usecases.customer.CreateCustomerResponder;
import hm.usecases.customer.PresentCustomerResponder;
import hm.usecases.customer.PresentableCustomer;
import hm.usecases.customer.UpdateCustomerResponder;
import hm.usecases.product.*;
import hm.usecases.sale.cart.AddProductToCartResponder;
import hm.usecases.sale.cart.PresentCartResponder;
import hm.usecases.sale.cart.PresentableCart;
import hm.usecases.sale.order.PresentSaleOrderResponder;
import hm.usecases.sale.order.SaleOrder;
import hm.usecases.sale.order.SubmitSaleOrderResponder;

class FakeResponder implements PresentCustomerResponder, CreateCustomerResponder, UpdateCustomerResponder, CreateProductResponder, PresentProductResponder, AddProductUnitResponder, UpdateProductResponder, AddProductToCartResponder, PresentCartResponder, SubmitSaleOrderResponder, PresentSaleOrderResponder {
    public boolean firstNameIsEmpty;
    public boolean lastNameIsEmpty;
    public String invalidId;
    public boolean idIsInvalid;
    public String createdWithId;
    public PresentableCustomer customer;
    public PresentableProduct product;
    public boolean nameIsEmpty;
    public boolean descriptionIsEmpty;
    public boolean pictureURIIsEmpty;
    public boolean priceIsNegative;
    public PresentableCart cart;
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

    public void customerFound(PresentableCustomer customer) {
        this.customer = customer;
    }

    public void productFound(PresentableProduct product) {
        this.product = product;
    }

    public void priceIsNegative() {
        priceIsNegative = true;
    }

    public void numberOfUnitsIsLessThanOne() {
        numberOfUnitsIsLessThanOne = true;
    }

    public void cartFound(PresentableCart cart) {
        this.cart = cart;
    }

    public void submitted(SaleOrder order) {
        this.order = order;
    }
}
