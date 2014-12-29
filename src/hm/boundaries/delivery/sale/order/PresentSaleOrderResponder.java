package hm.boundaries.delivery.sale.order;

import hm.boundaries.delivery.IdentityResponder;

public interface PresentSaleOrderResponder extends IdentityResponder {
    void saleOrderFound(PresentableSaleOrder order);
}
