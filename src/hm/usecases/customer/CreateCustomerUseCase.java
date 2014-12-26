package hm.usecases.customer;

import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.ValidatedUseCase;

public class CreateCustomerUseCase implements UseCase {
    private Gateway<Customer> gateway;
    private CustomerPersistenceRequest request;
    private CreateCustomerResponder responder;

    public static UseCase create(Gateway<Customer> gateway, CustomerPersistenceRequest request, CreateCustomerResponder responder) {
        UseCase useCase = new CreateCustomerUseCase(gateway, request, responder);
        return new ValidatedUseCase(useCase, responder, new CustomerDataValidation(request));
    }

    private CreateCustomerUseCase(Gateway<Customer> gateway, CustomerPersistenceRequest request, CreateCustomerResponder responder) {
        this.gateway = gateway;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        responder.createdWithId(gateway.persist(makeCustomer()).getId());
    }

    private Customer makeCustomer() {
        return Customer.create().with(request.getFirstName(), request.getLastName());
    }
}
