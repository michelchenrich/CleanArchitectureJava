package hm.usecases.customer;

import hm.usecases.commons.IdentityResponder;

public interface PresentCustomerResponder extends IdentityResponder {
    interface PresentableCustomer {
        String getName();
    }

    void customerFound(PresentableCustomer presentableCustomer);
}
