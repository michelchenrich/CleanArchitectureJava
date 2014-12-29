package hm.usecases.sale.cart;

import hm.boundaries.delivery.sale.cart.AddToCartRequest;
import hm.boundaries.delivery.sale.cart.AddToCartResponder;
import hm.usecases.Validation;

public class NumberOfUnitsValidation implements Validation {
    private AddToCartRequest request;
    private AddToCartResponder responder;

    public NumberOfUnitsValidation(AddToCartRequest request, AddToCartResponder responder) {
        this.request = request;
        this.responder = responder;
    }

    public boolean hasErrors() {
        return request.getNumberOfUnits() < 1;
    }

    public void sendErrors() {
        if (request.getNumberOfUnits() < 1)
            responder.numberOfUnitsIsLessThanOne();
    }
}
