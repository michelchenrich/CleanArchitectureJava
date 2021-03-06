package hm.usecases.sale.cart;

import hm.boundaries.delivery.UseCase;
import hm.boundaries.delivery.sale.cart.AddToCartRequest;
import hm.boundaries.delivery.sale.cart.AddToCartResponder;
import hm.boundaries.persistence.Memory;
import hm.domain.Customer;
import hm.domain.Product;
import hm.usecases.ValidatedUseCase;

public class AddToCartUseCase implements UseCase {
    private Memory<Customer> customerMemory;
    private Memory<Product> productMemory;
    private AddToCartRequest request;

    public static UseCase create(Memory<Customer> customerMemory, Memory<Product> productMemory, AddToCartRequest request, AddToCartResponder responder) {
        UseCase useCase = new AddToCartUseCase(customerMemory, productMemory, request);
        return new ValidatedUseCase(useCase, new AddToCartValidation(customerMemory, productMemory, request, responder));
    }

    private AddToCartUseCase(Memory<Customer> customerMemory, Memory<Product> productMemory, AddToCartRequest request) {
        this.customerMemory = customerMemory;
        this.productMemory = productMemory;
        this.request = request;
    }

    public void execute() {
        Product product = productMemory.findById(request.getProductId());
        int numberOfUnits = getAvailableNumberOfUnits(product);

        Customer customer = customerMemory.findById(request.getCustomerId());
        customerMemory.persist(customer.withItemInCart(product.getId(), product.getPrice(), numberOfUnits));
        productMemory.persist(product.withLessUnits(numberOfUnits));
    }

    private int getAvailableNumberOfUnits(Product product) {
        int numberOfUnits = request.getNumberOfUnits();
        if (numberOfUnits > product.getNumberOfUnits())
            numberOfUnits = product.getNumberOfUnits();
        return numberOfUnits;
    }
}
