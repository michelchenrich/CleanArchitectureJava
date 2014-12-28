package hm.usecases.sale.order;

import hm.usecases.commons.IdentityResponder;

public interface SubmitSaleOrderResponder extends IdentityResponder {
    void submitted(SaleOrder order);
}
