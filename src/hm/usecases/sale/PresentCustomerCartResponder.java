package hm.usecases.sale;

import hm.usecases.commons.IdentityResponder;

public interface PresentCustomerCartResponder extends IdentityResponder {
    void customerCartFound(Cart cart);
}
