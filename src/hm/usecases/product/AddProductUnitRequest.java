package hm.usecases.product;

import hm.usecases.commons.IdBasedRequest;

public interface AddProductUnitRequest extends IdBasedRequest {
    double getPrice();
    int getNumberOfUnits();
}
