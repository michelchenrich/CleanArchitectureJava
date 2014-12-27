package hm.usecases.sale;

import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.customer.Customer;

public class PresentCustomerCartUseCase implements UseCase {
    private Gateway<Customer> customerGateway;
    private IdBasedRequest request;
    private PresentCustomerCartResponder responder;

    public static UseCase create(Gateway<Customer> customerGateway, IdBasedRequest request, PresentCustomerCartResponder responder) {
        return new PresentCustomerCartUseCase(customerGateway, request, responder);
    }

    private PresentCustomerCartUseCase(Gateway<Customer> customerGateway, IdBasedRequest request, PresentCustomerCartResponder responder) {
        this.customerGateway = customerGateway;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Customer customer = customerGateway.findById(request.getId());
        responder.customerCartFound(customer.getCart());
    }
}
