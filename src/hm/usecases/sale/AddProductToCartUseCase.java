package hm.usecases.sale;

import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.customer.Customer;
import hm.usecases.product.Product;

public class AddProductToCartUseCase implements UseCase {
    private Gateway<Customer> customerGateway;
    private Gateway<Product> productGateway;
    private AddProductToCartRequest request;
    private AddProductToCartResponder responder;

    public static UseCase create(Gateway<Customer> customerGateway, Gateway<Product> productGateway, AddProductToCartRequest request, AddProductToCartResponder responder) {
        return new AddProductToCartUseCase(customerGateway, productGateway, request, responder);
    }

    private AddProductToCartUseCase(Gateway<Customer> customerGateway, Gateway<Product> productGateway, AddProductToCartRequest request, AddProductToCartResponder responder) {
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Product product = productGateway.findById(request.getProductId());
        product = product.withLessUnits(request.getNumberOfUnits());
        productGateway.persist(product);

        Customer customer = customerGateway.findById(request.getCustomerId());
        customer = customer.withNewItemInCart(product, request.getNumberOfUnits());
        customerGateway.persist(customer);
    }
}
