package hm.usecases.sale;

import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdentityResponder;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;
import hm.usecases.commons.Validation;
import hm.usecases.customer.Customer;
import hm.usecases.product.Product;

public class DropProductFromCartUseCase implements UseCase {
    private Gateway<Customer> customerGateway;
    private Gateway<Product> productGateway;
    private CartMovementRequest request;

    public static UseCase create(Gateway<Customer> customerGateway, Gateway<Product> productGateway, CartMovementRequest request, IdentityResponder responder) {
        UseCase useCase = new DropProductFromCartUseCase(customerGateway, productGateway, request);
        Validation customerIdValidation = new IdentityValidation(customerGateway, request.getCustomerId(), responder);
        Validation productIdValidation = new IdentityValidation(productGateway, request.getProductId(), responder);
        return new ValidatedUseCase(useCase, customerIdValidation, productIdValidation);
    }

    private DropProductFromCartUseCase(Gateway<Customer> customerGateway, Gateway<Product> productGateway, CartMovementRequest request) {
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
        this.request = request;
    }

    public void execute() {
        Customer customer = customerGateway.findById(request.getCustomerId());
        Product product = productGateway.findById(request.getProductId());

        int numberOfUnits = customer.getNumberOfUnitsInCartOf(product);

        productGateway.persist(product.withMoreUnits(numberOfUnits));
        customerGateway.persist(customer.withoutItemInCart(product));
    }
}
