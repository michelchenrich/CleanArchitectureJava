package hm.usecases.customer;

import hm.boundaries.delivery.UseCase;
import hm.boundaries.delivery.customer.CreateCustomerResponder;
import hm.boundaries.delivery.customer.PersistCustomerRequest;
import hm.boundaries.persistence.Memory;
import hm.domain.Customer;
import hm.usecases.ValidatedUseCase;

public class CreateCustomerUseCase implements UseCase {
    private Memory<Customer> memory;
    private PersistCustomerRequest request;
    private CreateCustomerResponder responder;

    public static UseCase create(Memory<Customer> memory, PersistCustomerRequest request, CreateCustomerResponder responder) {
        UseCase useCase = new CreateCustomerUseCase(memory, request, responder);
        return new ValidatedUseCase(useCase, new CustomerDataValidation(request, responder));
    }

    private CreateCustomerUseCase(Memory<Customer> memory, PersistCustomerRequest request, CreateCustomerResponder responder) {
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