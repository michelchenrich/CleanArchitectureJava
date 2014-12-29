package hm.usecases.customer;

import hm.boundaries.delivery.customer.CustomerDataResponder;
import hm.boundaries.delivery.customer.PersistCustomerRequest;
import hm.usecases.Validation;

public class CustomerDataValidation implements Validation {
    private String firstName;
    private String lastName;
    private CustomerDataResponder responder;

    public CustomerDataValidation(PersistCustomerRequest request, CustomerDataResponder responder) {
        firstName = makeSafe(request.getFirstName());
        lastName = makeSafe(request.getLastName());
        this.responder = responder;
    }

    public boolean hasErrors() {
        return firstName.isEmpty() || lastName.isEmpty();
    }

    public void sendErrors() {
        if (firstName.isEmpty())
            responder.firstNameIsEmpty();
        if (lastName.isEmpty())
            responder.lastNameIsEmpty();
    }

    private static String makeSafe(String input) {
        return input == null ? "" : input.trim();
    }
}