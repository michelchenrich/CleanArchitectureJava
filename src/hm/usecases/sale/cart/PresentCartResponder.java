package hm.usecases.sale.cart;

import hm.usecases.commons.IdentityResponder;
import hm.usecases.sale.PresentableSale;

public interface PresentCartResponder extends IdentityResponder {
    void cartFound(PresentableSale sale);
}
