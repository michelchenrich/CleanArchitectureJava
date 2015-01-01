package hm.usecases.sale.cart;

import hm.boundaries.delivery.sale.cart.AddToCartRequest;
import hm.boundaries.delivery.sale.cart.AddToCartResponder;
import hm.boundaries.persistence.Memory;
import hm.domain.Customer;
import hm.domain.Product;

public class AddToCartValidation extends CartMovementValidation {
    private AddToCartRequest request;
    private AddToCartResponder responder;

    public AddToCartValidation(Memory<Customer> customerMemory, Memory<Product> productMemory, AddToCartRequest request, AddToCartResponder responder) {
        super(customerMemory, productMemory, request, responder);
        this.request = request;
        this.responder = responder;
    }

    public boolean hasErrors() {
        return super.hasErrors() || request.getNumberOfUnits() < 1;
    }

    public void sendErrors() {
        super.sendErrors();
        if (request.getNumberOfUnits() < 1)
            responder.numberOfUnitsIsLessThanOne();
    }
}
