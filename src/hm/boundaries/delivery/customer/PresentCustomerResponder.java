package hm.boundaries.delivery.customer;

import hm.boundaries.delivery.IdentityResponder;

public interface PresentCustomerResponder extends IdentityResponder {
    void customerFound(PresentableCustomer customer);
}
