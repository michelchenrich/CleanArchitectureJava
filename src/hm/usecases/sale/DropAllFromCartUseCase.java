package hm.usecases.sale;

import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityResponder;
import hm.usecases.customer.Customer;
import hm.usecases.product.Product;

public class DropAllFromCartUseCase implements UseCase {
    private Gateway<Customer> customerGateway;
    private Gateway<Product> productGateway;
    private IdBasedRequest request;

    public static UseCase create(Gateway<Customer> customerGateway, Gateway<Product> productGateway, IdBasedRequest request, IdentityResponder responder) {
        return new DropAllFromCartUseCase(customerGateway, productGateway, request);
    }

    private DropAllFromCartUseCase(Gateway<Customer> customerGateway, Gateway<Product> productGateway, IdBasedRequest request) {
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
        this.request = request;
    }

    public void execute() {
        Customer customer = customerGateway.findById(request.getId());
        updateProducts(customer);
        customerGateway.persist(customer.withEmptyCart());
    }

    private void updateProducts(Customer customer) {
        for (CartItem item : customer.getCartItems()) updateProductUnits(item);
    }

    private void updateProductUnits(CartItem item) {
        Product product = productGateway.findById(item.getProductId());
        product = product.withMoreUnits(item.getNumberOfUnits());
        productGateway.persist(product);
    }
}