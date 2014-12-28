package hm.usecases.sale.cart;

import hm.entities.Customer;
import hm.entities.Gateway;
import hm.entities.Item;
import hm.entities.Product;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityResponder;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;

public class DropAllFromCartUseCase implements UseCase {
    private Gateway<Customer> customerGateway;
    private Gateway<Product> productGateway;
    private IdBasedRequest request;

    public static UseCase create(Gateway<Customer> customerGateway, Gateway<Product> productGateway, IdBasedRequest request, IdentityResponder responder) {
        UseCase useCase = new DropAllFromCartUseCase(customerGateway, productGateway, request);
        return new ValidatedUseCase(useCase, new IdentityValidation(customerGateway, request, responder));
    }

    private DropAllFromCartUseCase(Gateway<Customer> customerGateway, Gateway<Product> productGateway, IdBasedRequest request) {
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
        this.request = request;
    }

    public void execute() {
        Customer customer = customerGateway.findById(request.getId());
        restoreUnits(customer);
        customerGateway.persist(customer.withEmptyCart());
    }

    private void restoreUnits(Customer customer) {
        for (Item item : customer.getCartItems()) restoreProductUnits(item);
    }

    private void restoreProductUnits(Item item) {
        Product product = productGateway.findById(item.getProductId());
        productGateway.persist(product.withMoreUnits(item.getNumberOfUnits()));
    }
}