package hm.usecases.sale.cart;

import hm.domain.Customer;
import hm.domain.Memory;
import hm.domain.Product;
import hm.usecases.UseCase;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;
import hm.usecases.commons.Validation;

public class AddProductToCartUseCase implements UseCase {
    private Memory<Customer> customerMemory;
    private Memory<Product> productMemory;
    private AddProductToCartRequest request;

    public static UseCase create(Memory<Customer> customerMemory, Memory<Product> productMemory, AddProductToCartRequest request, AddProductToCartResponder responder) {
        UseCase useCase = new AddProductToCartUseCase(customerMemory, productMemory, request);
        Validation customerIdValidation = new IdentityValidation(customerMemory, request.getCustomerId(), responder);
        Validation productIdValidation = new IdentityValidation(productMemory, request.getProductId(), responder);
        Validation numberOfUnitsValidation = new NumberOfUnitsValidation(request, responder);
        return new ValidatedUseCase(useCase, customerIdValidation, productIdValidation, numberOfUnitsValidation);
    }

    private AddProductToCartUseCase(Memory<Customer> customerMemory, Memory<Product> productMemory, AddProductToCartRequest request) {
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
