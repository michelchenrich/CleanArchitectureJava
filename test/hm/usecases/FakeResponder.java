package hm.usecases;

import hm.usecases.commons.IdentityResponder;
import hm.usecases.customer.CreateCustomerResponder;
import hm.usecases.customer.PresentCustomerResponder;
import hm.usecases.customer.PresentableCustomer;
import hm.usecases.customer.UpdateCustomerResponder;
import hm.usecases.product.CreateProductResponder;
import hm.usecases.product.PresentProductResponder;
import hm.usecases.product.PresentableProduct;
import hm.usecases.product.UpdateProductResponder;
import hm.usecases.sale.PresentableSale;
import hm.usecases.sale.cart.AddProductToCartResponder;
import hm.usecases.sale.cart.PresentCartResponder;
import hm.usecases.sale.order.PresentSaleOrderResponder;
import hm.usecases.sale.order.PresentableSaleOrder;
import hm.usecases.sale.order.SubmitSaleOrderResponder;

class FakeResponder implements PresentCustomerResponder, CreateCustomerResponder, UpdateCustomerResponder, CreateProductResponder, PresentProductResponder, UpdateProductResponder, AddProductToCartResponder, PresentCartResponder, SubmitSaleOrderResponder, IdentityResponder, PresentSaleOrderResponder {
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
    public PresentableSale sale;
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

    public void cartFound(PresentableSale sale) {
        this.sale = sale;
    }

    public void saleOrderFound(PresentableSaleOrder order) {
        this.order = order;
    }
}
