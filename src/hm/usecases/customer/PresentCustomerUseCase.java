package hm.usecases.customer;

import hm.boundaries.delivery.IdBasedRequest;
import hm.boundaries.delivery.UseCase;
import hm.boundaries.delivery.customer.PresentCustomerResponder;
import hm.boundaries.delivery.customer.PresentableCustomer;
import hm.boundaries.persistence.Memory;
import hm.domain.Customer;
import hm.usecases.IdentityValidation;
import hm.usecases.ValidatedUseCase;

public class PresentCustomerUseCase implements UseCase {
    private Memory<Customer> memory;
    private IdBasedRequest request;
    private PresentCustomerResponder responder;

    public static UseCase create(Memory<Customer> memory, IdBasedRequest request, PresentCustomerResponder responder) {
        UseCase useCase = new PresentCustomerUseCase(memory, request, responder);
        return new ValidatedUseCase(useCase, new IdentityValidation(memory, request, responder));
    }

    private PresentCustomerUseCase(Memory<Customer> memory, IdBasedRequest request, PresentCustomerResponder responder) {
        this.memory = memory;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Customer customer = memory.findById(request.getId());
        responder.customerFound(new PresentableCustomer(customer));
    }
}
