package hm.usecases.customer;

import hm.domain.Customer;
import hm.domain.Memory;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;

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
