package hm.usecases.customer;

import hm.boundaries.delivery.customer.CustomerDataResponder;
import hm.boundaries.delivery.customer.PersistCustomerRequest;
import hm.usecases.Validation;

public class CustomerDataValidation implements Validation {
    private String firstName;
    private String lastName;
    private CustomerDataResponder responder;
    private PersistCustomerRequest request;

    public CustomerDataValidation(PersistCustomerRequest request, CustomerDataResponder responder) {
        this.request = request;
        this.responder = responder;
    }

    public boolean hasErrors() {
        initializeFields();
        return firstName.isEmpty() || lastName.isEmpty();
    }

    public void sendErrors() {
        initializeFields();
        if (firstName.isEmpty())
            responder.firstNameIsEmpty();
        if (lastName.isEmpty())
            responder.lastNameIsEmpty();
    }

    private void initializeFields() {
        firstName = makeSafe(request.getFirstName());
        lastName = makeSafe(request.getLastName());
    }

    private static String makeSafe(String input) {
        return input == null ? "" : input.trim();
    }
}