package hm.usecases.sale.cart;

import hm.boundaries.delivery.sale.cart.ChangeCartRequest;
import hm.boundaries.delivery.sale.cart.ChangeCartResponder;
import hm.boundaries.persistence.Memory;
import hm.domain.Customer;
import hm.domain.Product;
import hm.usecases.Validation;

public class CartMovementValidation implements Validation {
    private Memory<Customer> customerMemory;
    private Memory<Product> productMemory;
    private ChangeCartRequest request;
    private ChangeCartResponder responder;

    public CartMovementValidation(Memory<Customer> customerMemory, Memory<Product> productMemory, ChangeCartRequest request, ChangeCartResponder responder) {
        this.customerMemory = customerMemory;
        this.productMemory = productMemory;
        this.request = request;
        this.responder = responder;
    }

    public boolean hasErrors() {
        return !customerMemory.existsWithId(request.getCustomerId()) || !productMemory.existsWithId(request.getProductId());
    }

    public void sendErrors() {
        if (!customerMemory.existsWithId(request.getCustomerId()))
            responder.invalidCustomerId(request.getCustomerId());
        if (!productMemory.existsWithId(request.getProductId()))
            responder.invalidProductId(request.getProductId());
    }
}
