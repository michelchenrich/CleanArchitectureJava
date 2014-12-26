package hm;

import hm.usecases.Context;
import hm.usecases.Gateway;
import hm.usecases.customer.Customer;

class FakeContext implements Context {
    private Gateway<Customer> customers;

    FakeContext(Gateway<Customer> customers) {
        this.customers = customers;
    }

    public Gateway<Customer> getCustomers() {
        return customers;
    }
}
