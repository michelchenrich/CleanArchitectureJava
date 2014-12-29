package hm.usecases.sale.order;

import hm.usecases.commons.IdentityResponder;

public interface PresentSaleOrderResponder extends IdentityResponder {
    void saleOrderFound(PresentableSaleOrder order);
}
