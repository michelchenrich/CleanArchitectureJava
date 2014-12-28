package hm.usecases.sale.cart;

import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;
import hm.usecases.customer.Customer;

public class PresentCustomerCartUseCase implements UseCase {
    private Gateway<Customer> gateway;
    private IdBasedRequest request;
    private PresentCustomerCartResponder responder;

    public static UseCase create(Gateway<Customer> gateway, IdBasedRequest request, PresentCustomerCartResponder responder) {
        UseCase useCase = new PresentCustomerCartUseCase(gateway, request, responder);
        return new ValidatedUseCase(useCase, new IdentityValidation(gateway, request, responder));
    }

    private PresentCustomerCartUseCase(Gateway<Customer> gateway, IdBasedRequest request, PresentCustomerCartResponder responder) {
        this.gateway = gateway;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Customer customer = gateway.findById(request.getId());
        responder.cartFound(customer.getCartItems());
    }
}
