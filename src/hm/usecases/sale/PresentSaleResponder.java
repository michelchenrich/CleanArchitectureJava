package hm.usecases.sale;

import hm.usecases.commons.IdentityResponder;

public interface PresentSaleResponder extends IdentityResponder {
    void saleFound(PresentableSale sale);
}
