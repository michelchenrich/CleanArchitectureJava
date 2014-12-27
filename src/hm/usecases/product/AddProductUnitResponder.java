package hm.usecases.product;

import hm.usecases.commons.IdentityResponder;

public interface AddProductUnitResponder extends IdentityResponder {
    void priceIsNegative();
}
