package hm.usecases.customer;

public interface CreateCustomerResponder extends CustomerDataResponder {
    void createdWithId(String id);
}
