package hm.usecases.sale.cart;

import hm.usecases.commons.Validation;

public class NumberOfUnitsValidation implements Validation {
    private AddProductToCartRequest request;
    private AddProductToCartResponder responder;

    public NumberOfUnitsValidation(AddProductToCartRequest request, AddProductToCartResponder responder) {
        this.request = request;
        this.responder = responder;
    }

    public boolean hasErrors() {
        return request.getNumberOfUnits() < 1;
    }

    public void sendErrors() {
        if (request.getNumberOfUnits() < 1) responder.numberOfUnitsIsLessThanOne();
    }
}
