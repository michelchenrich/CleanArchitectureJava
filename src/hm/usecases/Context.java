package hm.usecases;

import hm.usecases.customer.Customer;

public interface Context {
    Gateway<Customer> getCustomers();
}
