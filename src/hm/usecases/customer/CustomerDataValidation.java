package hm.usecases.customer;

import hm.usecases.commons.Validation;

public class CustomerDataValidation implements Validation {
    private String firstName;
    private String lastName;
    private CustomerDataResponder responder;

    public CustomerDataValidation(CustomerPersistenceRequest request, CustomerDataResponder responder) {
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