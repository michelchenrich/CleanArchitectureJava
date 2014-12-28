package hm.usecases.sale;

import hm.usecases.commons.IdentityResponder;

public interface SubmitSaleOrderResponder extends IdentityResponder {
    void submitted(SaleOrder order);
}
