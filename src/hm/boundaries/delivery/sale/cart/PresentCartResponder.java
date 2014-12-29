package hm.boundaries.delivery.sale.cart;

import hm.boundaries.delivery.IdentityResponder;

public interface PresentCartResponder extends IdentityResponder {
    void cartFound(PresentableCart cart);
}
