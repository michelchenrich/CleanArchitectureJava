package hm.usecases.sale.cart;

import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;
import hm.usecases.customer.Customer;
import hm.usecases.product.Product;

public class PresentCartUseCase implements UseCase {
    private Gateway<Customer> customerGateway;
    private Gateway<Product> productGateway;
    private IdBasedRequest request;
    private PresentCartResponder responder;

    public static UseCase create(Gateway<Customer> customerGateway, Gateway<Product> productGateway, IdBasedRequest request, PresentCartResponder responder) {
        UseCase useCase = new PresentCartUseCase(customerGateway, productGateway, request, responder);
        return new ValidatedUseCase(useCase, new IdentityValidation(customerGateway, request, responder));
    }

    private PresentCartUseCase(Gateway<Customer> customerGateway, Gateway<Product> productGateway, IdBasedRequest request, PresentCartResponder responder) {
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Customer customer = customerGateway.findById(request.getId());
        responder.cartFound(new PresentableCart(customer.getCartItems(), productGateway));
    }
}
