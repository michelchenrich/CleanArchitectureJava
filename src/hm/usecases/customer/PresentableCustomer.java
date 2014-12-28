package hm.usecases.customer;

import hm.domain.Customer;

public class PresentableCustomer {
    private Customer customer;

    public PresentableCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getName() {
        return String.format("%s, %s", customer.getLastName(), customer.getFirstName());
    }
}
