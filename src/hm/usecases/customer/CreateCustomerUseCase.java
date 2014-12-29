package hm.usecases.customer;

import hm.domain.Customer;
import hm.domain.Memory;
import hm.usecases.UseCase;
import hm.usecases.commons.ValidatedUseCase;

public class CreateCustomerUseCase implements UseCase {
    private Memory<Customer> memory;
    private CustomerPersistenceRequest request;
    private CreateCustomerResponder responder;

    public static UseCase create(Memory<Customer> memory, CustomerPersistenceRequest request, CreateCustomerResponder responder) {
        UseCase useCase = new CreateCustomerUseCase(memory, request, responder);
        return new ValidatedUseCase(useCase, new CustomerDataValidation(request, responder));
    }

    private CreateCustomerUseCase(Memory<Customer> memory, CustomerPersistenceRequest request, CreateCustomerResponder responder) {
        this.memory = memory;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Customer customer = Customer.create();
        customer = customer.withFirstName(request.getFirstName());
        customer = customer.withLastName(request.getLastName());
        customer = memory.persist(customer);
        responder.createdWithId(customer.getId());
    }
}