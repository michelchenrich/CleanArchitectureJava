package hm.usecases.sale.cart;

import hm.usecases.commons.IdentityResponder;

public interface PresentCartResponder extends IdentityResponder {
    void cartFound(PresentableCart cart);
}
