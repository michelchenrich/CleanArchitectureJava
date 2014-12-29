package hm.usecases.sale.cart;

import hm.boundaries.delivery.IdBasedRequest;
import hm.boundaries.delivery.IdentityResponder;
import hm.boundaries.delivery.UseCase;
import hm.boundaries.persistence.Memory;
import hm.domain.Customer;
import hm.domain.Item;
import hm.domain.Product;
import hm.usecases.IdentityValidation;
import hm.usecases.ValidatedUseCase;

public class DropCartUseCase implements UseCase {
    private Memory<Customer> customerMemory;
    private Memory<Product> productMemory;
    private IdBasedRequest request;

    public static UseCase create(Memory<Customer> customerMemory, Memory<Product> productMemory, IdBasedRequest request, IdentityResponder responder) {
        UseCase useCase = new DropCartUseCase(customerMemory, productMemory, request);
        return new ValidatedUseCase(useCase, new IdentityValidation(customerMemory, request, responder));
    }

    private DropCartUseCase(Memory<Customer> customerMemory, Memory<Product> productMemory, IdBasedRequest request) {
        this.customerMemory = customerMemory;
        this.productMemory = productMemory;
        this.request = request;
    }

    public void execute() {
        Customer customer = customerMemory.findById(request.getId());
        restoreUnits(customer);
        customerMemory.persist(customer.withEmptyCart());
    }

    private void restoreUnits(Customer customer) {
        for (Item item : customer.getCartItems())
            restoreProductUnits(item);
    }

    private void restoreProductUnits(Item item) {
        Product product = productMemory.findById(item.getProductId());
        productMemory.persist(product.withMoreUnits(item.getNumberOfUnits()));
    }
}