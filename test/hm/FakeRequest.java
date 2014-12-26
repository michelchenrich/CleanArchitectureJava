package hm;

import hm.usecases.commons.IdBasedRequest;
import hm.usecases.customer.CustomerPersistenceRequest;
import hm.usecases.customer.UpdateCustomerRequest;

class FakeRequest implements IdBasedRequest, CustomerPersistenceRequest, UpdateCustomerRequest {
    public String id;
    public String firstName;
    public String lastName;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
