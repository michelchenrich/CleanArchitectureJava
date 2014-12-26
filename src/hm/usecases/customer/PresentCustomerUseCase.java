package hm.usecases.customer;

import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;
import hm.usecases.customer.PresentCustomerResponder.PresentableCustomer;

public class PresentCustomerUseCase implements UseCase {
    private Gateway<Customer> gateway;
    private IdBasedRequest request;
    private PresentCustomerResponder responder;

    public static UseCase create(Gateway<Customer> gateway, IdBasedRequest request, PresentCustomerResponder responder) {
        UseCase useCase = new PresentCustomerUseCase(gateway, request, responder);
        return new ValidatedUseCase(useCase, responder, new IdentityValidation(request, gateway));
    }

    private PresentCustomerUseCase(Gateway<Customer> gateway, IdBasedRequest request, PresentCustomerResponder responder) {
        this.gateway = gateway;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Customer customer = gateway.findById(request.getId());
        responder.customerFound(makePresentable(customer));
    }

    private PresentableCustomer makePresentable(final Customer customer) {
        return () -> String.format("%s, %s", customer.getLastName(), customer.getFirstName());
    }
}
