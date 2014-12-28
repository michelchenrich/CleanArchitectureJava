package hm.usecases.customer;

import hm.entities.Customer;
import hm.entities.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;

public class UpdateCustomerUseCase implements UseCase {
    private Gateway<Customer> gateway;
    private UpdateCustomerRequest request;

    public static UseCase create(Gateway<Customer> gateway, UpdateCustomerRequest request, UpdateCustomerResponder responder) {
        UseCase useCase = new UpdateCustomerUseCase(gateway, request);
        return new ValidatedUseCase(useCase, new IdentityValidation(gateway, request, responder), new CustomerDataValidation(request, responder));
    }

    private UpdateCustomerUseCase(Gateway<Customer> gateway, UpdateCustomerRequest request) {
        this.gateway = gateway;
        this.request = request;
    }

    public void execute() {
        Customer customer = gateway.findById(request.getId());
        customer = customer.withFirstName(request.getFirstName());
        customer = customer.withLastName(request.getLastName());
        gateway.persist(customer);
    }
}