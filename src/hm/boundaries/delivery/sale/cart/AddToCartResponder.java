package hm.boundaries.delivery.sale.cart;

import hm.boundaries.delivery.IdentityResponder;

public interface AddToCartResponder extends IdentityResponder {
    void numberOfUnitsIsLessThanOne();
}
