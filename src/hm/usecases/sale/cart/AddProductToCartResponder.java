package hm.usecases.sale.cart;

import hm.usecases.commons.IdentityResponder;

public interface AddProductToCartResponder extends IdentityResponder {
    void numberOfUnitsIsLessThanOne();
}
