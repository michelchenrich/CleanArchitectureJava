package hm.usecases.customer;

import hm.usecases.commons.IdentityResponder;

public interface PresentCustomerResponder extends IdentityResponder {
    void customerFound(PresentableCustomer customer);
}
