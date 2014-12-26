package hm.usecases.customer;

import hm.usecases.commons.Validation;

public class CustomerDataValidation implements Validation {
    private String firstName;
    private String lastName;

    public CustomerDataValidation(CustomerPersistenceRequest request) {
        firstName = makeSafe(request.getFirstName());
        lastName = makeSafe(request.getLastName());
    }

    public boolean isOK() {
        return !firstName.isEmpty() && !lastName.isEmpty();
    }

    public void sendErrorsTo(Object output) {
        CustomerDataResponder responder = (CustomerDataResponder) output;
        if (firstName.isEmpty()) responder.firstNameIsEmpty();
        if (lastName.isEmpty()) responder.lastNameIsEmpty();
    }

    private static String makeSafe(String input) {
        return input == null ? "" : input.trim();
    }
}