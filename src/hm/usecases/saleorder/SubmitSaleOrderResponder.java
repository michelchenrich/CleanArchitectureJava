package hm.usecases.saleorder;

import hm.usecases.commons.IdentityResponder;

public interface SubmitSaleOrderResponder extends IdentityResponder {
    void createdWithId(String id);
}
