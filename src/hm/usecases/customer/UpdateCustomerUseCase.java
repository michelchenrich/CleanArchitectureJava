package hm.usecases.customer;

import hm.usecases.Context;
import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;
import hm.usecases.commons.Validation;
import hm.usecases.commons.ValidationCombiner;

public class UpdateCustomerUseCase implements UseCase {
    private Gateway<Customer> customers;
    private UpdateCustomerRequest request;

    public static UseCase create(Context context, UpdateCustomerRequest request, UpdateCustomerResponder responder) {
        UpdateCustomerUseCase useCase = new UpdateCustomerUseCase(context, request);
        return new ValidatedUseCase(useCase, responder, makeInputCheck(context, request));
    }

    private static Validation makeInputCheck(Context context, UpdateCustomerRequest request) {
        Validation idValidation = new IdentityValidation(request, context.getCustomers());
        Validation dataValidation = new CustomerDataValidation(request);
        return new ValidationCombiner(idValidation, dataValidation);
    }

    private UpdateCustomerUseCase(Context context, UpdateCustomerRequest request) {
        customers = context.getCustomers();
        this.request = request;
    }

    public void execute() {
        customers.persist(makeCustomer());
    }

    private Customer makeCustomer() {
        return customers.findById(request.getId()).with(request.getFirstName(), request.getLastName());
    }
}
