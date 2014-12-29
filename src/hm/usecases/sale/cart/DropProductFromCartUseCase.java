package hm.usecases.sale.cart;

import hm.domain.Customer;
import hm.domain.Memory;
import hm.domain.Product;
import hm.usecases.UseCase;
import hm.usecases.commons.IdentityResponder;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;
import hm.usecases.commons.Validation;

public class DropProductFromCartUseCase implements UseCase {
    private Memory<Customer> customerMemory;
    private Memory<Product> productMemory;
    private CartMovementRequest request;

    public static UseCase create(Memory<Customer> customerMemory, Memory<Product> productMemory, CartMovementRequest request, IdentityResponder responder) {
        UseCase useCase = new DropProductFromCartUseCase(customerMemory, productMemory, request);
        Validation customerIdValidation = new IdentityValidation(customerMemory, request.getCustomerId(), responder);
        Validation productIdValidation = new IdentityValidation(productMemory, request.getProductId(), responder);
        return new ValidatedUseCase(useCase, customerIdValidation, productIdValidation);
    }

    private DropProductFromCartUseCase(Memory<Customer> customerMemory, Memory<Product> productMemory, CartMovementRequest request) {
        this.customerMemory = customerMemory;
        this.productMemory = productMemory;
        this.request = request;
    }

    public void execute() {
        String productId = request.getProductId();

        Customer customer = customerMemory.findById(request.getCustomerId());
        Product product = productMemory.findById(productId);

        int numberOfUnits = customer.getNumberOfUnitsInCartOf(productId);

        productMemory.persist(product.withMoreUnits(numberOfUnits));
        customerMemory.persist(customer.withoutItemInCart(productId));
    }
}
