package hm.usecases.sale.cart;

import hm.boundaries.delivery.UseCase;
import hm.boundaries.delivery.sale.cart.ChangeCartRequest;
import hm.boundaries.delivery.sale.cart.ChangeCartResponder;
import hm.boundaries.persistence.Memory;
import hm.domain.Customer;
import hm.domain.Product;
import hm.usecases.ValidatedUseCase;

public class RemoveFromCartUseCase implements UseCase {
    private Memory<Customer> customerMemory;
    private Memory<Product> productMemory;
    private ChangeCartRequest request;

    public static UseCase create(Memory<Customer> customerMemory, Memory<Product> productMemory, ChangeCartRequest request, ChangeCartResponder responder) {
        UseCase useCase = new RemoveFromCartUseCase(customerMemory, productMemory, request);
        return new ValidatedUseCase(useCase, new CartMovementValidation(customerMemory, productMemory, request, responder));
    }

    private RemoveFromCartUseCase(Memory<Customer> customerMemory, Memory<Product> productMemory, ChangeCartRequest request) {
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
