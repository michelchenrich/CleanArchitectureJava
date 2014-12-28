package hm.usecases.cart;

import hm.usecases.commons.IdentityResponder;

public interface AddProductToCartResponder extends IdentityResponder {
    void numberOfUnitsIsLessThanOne();
}
