package hm.usecases.product;

import hm.usecases.commons.Validation;

public class UnitPriceValidation implements Validation {
    private AddProductUnitRequest request;
    private AddProductUnitResponder responder;

    public UnitPriceValidation(AddProductUnitRequest request, AddProductUnitResponder responder) {
        this.request = request;
        this.responder = responder;
    }

    public boolean hasErrors() {
        return request.getPrice() < 0;
    }

    public void sendErrors() {
        if (request.getPrice() < 0) responder.priceIsNegative();
    }
}