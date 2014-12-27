package hm.usecases.sale;

import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdentityResponder;
import hm.usecases.customer.Customer;
import hm.usecases.product.Product;

public class DropProductFromCartUseCase implements UseCase {
    private Gateway<Customer> customerGateway;
    private Gateway<Product> productGateway;
    private CartMovementRequest request;
    private IdentityResponder responder;

    public static UseCase create(Gateway<Customer> customerGateway, Gateway<Product> productGateway, CartMovementRequest request, IdentityResponder responder) {
        return new DropProductFromCartUseCase(customerGateway, productGateway, request, responder);
    }

    private DropProductFromCartUseCase(Gateway<Customer> customerGateway, Gateway<Product> productGateway, CartMovementRequest request, IdentityResponder responder) {
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Customer customer = customerGateway.findById(request.getCustomerId());
        Product product = productGateway.findById(request.getProductId());

        int numberOfUnits = customer.getNumberOfUnitsInCartOf(product);

        productGateway.persist(product.withMoreUnits(numberOfUnits));
        customerGateway.persist(customer.withoutItemInCart(product));
    }
}
