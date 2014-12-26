package hm;

import hm.usecases.Gateway;
import hm.usecases.NoSuchEntityException;
import hm.usecases.customer.Customer;

import java.util.HashMap;
import java.util.Map;

class FakeGateway implements Gateway<Customer> {
    private Map<String, Customer> customers = new HashMap<String, Customer>();
    private int incrementalId;

    public boolean containsWithId(String id) {
        return customers.containsKey(id);
    }

    public Customer persist(Customer customer) {
        if (customer.getId().isEmpty())
            customer = customer.withId(nextId());
        customers.put(customer.getId(), customer);
        return customer;
    }

    public Customer findById(String id) {
        if (!containsWithId(id)) throw new NoSuchEntityException(id);
        return customers.get(id);
    }

    private String nextId() {
        return String.valueOf(++incrementalId);
    }
}
