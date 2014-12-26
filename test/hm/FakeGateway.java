package hm;

import hm.usecases.Gateway;
import hm.usecases.NoSuchKeyException;
import hm.usecases.customer.Customer;

import java.util.HashMap;
import java.util.Map;

class FakeGateway implements Gateway<Customer> {
    private Map<String, Customer> customers = new HashMap<String, Customer>();
    private int incrementalId;

    public boolean containsWithId(String key) {
        return customers.containsKey(key);
    }

    public Customer persist(Customer customer) {
        if (customer.getId().isEmpty())
            customer = customer.withId(nextId());
        customers.put(customer.getId(), customer);
        return customer;
    }

    public Customer findById(String key) {
        if (!containsWithId(key)) throw new NoSuchKeyException(key);
        return customers.get(key);
    }

    private String nextId() {
        return String.valueOf(++incrementalId);
    }
}
