package hm.usecases.customer;

import hm.usecases.Context;
import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.ValidatedUseCase;

public class CreateCustomerUseCase implements UseCase {
    private Gateway<Customer> customers;
    private CustomerPersistenceRequest request;
    private CreateCustomerResponder responder;

    public static UseCase create(Context context, CustomerPersistenceRequest request, CreateCustomerResponder responder) {
        CreateCustomerUseCase useCase = new CreateCustomerUseCase(context, request, responder);
        return new ValidatedUseCase(useCase, responder, new CustomerDataValidation(request));
    }

    private CreateCustomerUseCase(Context context, CustomerPersistenceRequest request, CreateCustomerResponder responder) {
        customers = context.getCustomers();
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        responder.createdWithId(customers.persist(makeCustomer()).getId());
    }

    private Customer makeCustomer() {
        return Customer.create().with(request.getFirstName(), request.getLastName());
    }
}
