package hm.usecases.customer;

import hm.boundaries.delivery.UseCase;
import hm.boundaries.delivery.customer.UpdateCustomerRequest;
import hm.boundaries.delivery.customer.UpdateCustomerResponder;
import hm.boundaries.persistence.Memory;
import hm.domain.Customer;
import hm.usecases.IdentityValidation;
import hm.usecases.ValidatedUseCase;

public class UpdateCustomerUseCase implements UseCase {
    private Memory<Customer> memory;
    private UpdateCustomerRequest request;

    public static UseCase create(Memory<Customer> memory, UpdateCustomerRequest request, UpdateCustomerResponder responder) {
        UseCase useCase = new UpdateCustomerUseCase(memory, request);
        return new ValidatedUseCase(useCase, new IdentityValidation(memory, request, responder), new CustomerDataValidation(request, responder));
    }

    private UpdateCustomerUseCase(Memory<Customer> memory, UpdateCustomerRequest request) {
        this.memory = memory;
        this.request = request;
    }

    public void execute() {
        Customer customer = memory.findById(request.getId());
        customer = customer.withFirstName(request.getFirstName());
        customer = customer.withLastName(request.getLastName());
        memory.persist(customer);
    }
}