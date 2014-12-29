package hm.boundaries.delivery.sale.order;

import hm.boundaries.delivery.IdentityResponder;

public interface SubmitSaleOrderResponder extends IdentityResponder {
    void createdWithId(String id);
}
