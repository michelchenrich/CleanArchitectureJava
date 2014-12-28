package hm.usecases.customer;

import hm.domain.Customer;
import hm.domain.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;

public class PresentCustomerUseCase implements UseCase {
    private Gateway<Customer> gateway;
    private IdBasedRequest request;
    private PresentCustomerResponder responder;

    public static UseCase create(Gateway<Customer> gateway, IdBasedRequest request, PresentCustomerResponder responder) {
        UseCase useCase = new PresentCustomerUseCase(gateway, request, responder);
        return new ValidatedUseCase(useCase, new IdentityValidation(gateway, request, responder));
    }

    private PresentCustomerUseCase(Gateway<Customer> gateway, IdBasedRequest request, PresentCustomerResponder responder) {
        this.gateway = gateway;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Customer customer = gateway.findById(request.getId());
        responder.customerFound(new PresentableCustomer(customer));
    }
}
