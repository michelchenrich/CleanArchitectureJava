package hm.usecases;

import hm.boundaries.delivery.IdentityResponder;
import hm.boundaries.delivery.customer.CreateCustomerResponder;
import hm.boundaries.delivery.customer.PresentCustomerResponder;
import hm.boundaries.delivery.customer.PresentableCustomer;
import hm.boundaries.delivery.customer.UpdateCustomerResponder;
import hm.boundaries.delivery.product.CreateProductResponder;
import hm.boundaries.delivery.product.PresentProductResponder;
import hm.boundaries.delivery.product.PresentableProduct;
import hm.boundaries.delivery.product.UpdateProductResponder;
import hm.boundaries.delivery.sale.cart.AddToCartResponder;
import hm.boundaries.delivery.sale.cart.PresentCartResponder;
import hm.boundaries.delivery.sale.cart.PresentableCart;
import hm.boundaries.delivery.sale.order.PresentSaleOrderResponder;
import hm.boundaries.delivery.sale.order.PresentableSaleOrder;
import hm.boundaries.delivery.sale.order.SubmitSaleOrderResponder;

class FakeResponder implements PresentCustomerResponder, CreateCustomerResponder, UpdateCustomerResponder, CreateProductResponder, PresentProductResponder, UpdateProductResponder, AddToCartResponder, PresentCartResponder, SubmitSaleOrderResponder, IdentityResponder, PresentSaleOrderResponder {
    public PresentableCustomer customer;
    public boolean firstNameIsEmpty;
    public boolean lastNameIsEmpty;
    public boolean idIsInvalid;
    public String invalidId;
    public String createdWithId;
    public PresentableProduct product;
    public boolean nameIsEmpty;
    public boolean descriptionIsEmpty;
    public boolean pictureURIIsEmpty;
    public boolean priceIsNegative;
    public boolean numberOfUnitsIsLessThanOne;
    public PresentableCart sale;
    public PresentableSaleOrder order;

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
        this.sale = cart;
    }

    public void saleOrderFound(PresentableSaleOrder order) {
        this.order = order;
    }
}
