package hm.usecases.sale;

import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;
import hm.usecases.commons.Validation;
import hm.usecases.customer.Customer;
import hm.usecases.product.Product;

public class AddProductToCartUseCase implements UseCase {
    private Gateway<Customer> customerGateway;
    private Gateway<Product> productGateway;
    private AddProductToCartRequest request;

    public static UseCase create(Gateway<Customer> customerGateway, Gateway<Product> productGateway, AddProductToCartRequest request, AddProductToCartResponder responder) {
        UseCase useCase = new AddProductToCartUseCase(customerGateway, productGateway, request);
        Validation customerIdValidation = new IdentityValidation(customerGateway, request.getCustomerId(), responder);
        Validation productIdValidation = new IdentityValidation(productGateway, request.getProductId(), responder);
        Validation numberOfUnitsValidation = new NumberOfUnitsValidation(request, responder);
        return new ValidatedUseCase(useCase, customerIdValidation, productIdValidation, numberOfUnitsValidation);
    }

    private AddProductToCartUseCase(Gateway<Customer> customerGateway, Gateway<Product> productGateway, AddProductToCartRequest request) {
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
        this.request = request;
    }

    public void execute() {
        Product product = productGateway.findById(request.getProductId());
        int numberOfUnits = getAvailableNumberOfUnits(product);

        Customer customer = customerGateway.findById(request.getCustomerId());
        customerGateway.persist(customer.withNewItemInCart(product, numberOfUnits));
        productGateway.persist(product.withLessUnits(numberOfUnits));
    }

    private int getAvailableNumberOfUnits(Product product) {
        int numberOfUnits = request.getNumberOfUnits();
        if (numberOfUnits > product.getNumberOfUnits())
            numberOfUnits = product.getNumberOfUnits();
        return numberOfUnits;
    }
}
