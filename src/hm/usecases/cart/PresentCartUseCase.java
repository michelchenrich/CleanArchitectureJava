package hm.usecases.cart;

import com.google.common.collect.ImmutableList;
import hm.entities.Customer;
import hm.entities.Gateway;
import hm.entities.Item;
import hm.entities.Product;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;

import java.util.List;

public class PresentCartUseCase implements UseCase {
    private Gateway<Customer> customerGateway;
    private Gateway<Product> productGateway;
    private IdBasedRequest request;
    private PresentCartResponder responder;

    public static UseCase create(Gateway<Customer> customerGateway, Gateway<Product> productGateway, IdBasedRequest request, PresentCartResponder responder) {
        UseCase useCase = new PresentCartUseCase(customerGateway, productGateway, request, responder);
        return new ValidatedUseCase(useCase, new IdentityValidation(customerGateway, request, responder));
    }

    private PresentCartUseCase(Gateway<Customer> customerGateway, Gateway<Product> productGateway, IdBasedRequest request, PresentCartResponder responder) {
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Customer customer = customerGateway.findById(request.getId());
        responder.cartFound(makePresentableCart(customer));
    }

    private PresentableCart makePresentableCart(Customer customer) {
        return new PresentableCart(asPresentable(customer.getCartItems()));
    }

    private List<PresentableItem> asPresentable(List<Item> cartItems) {
        ImmutableList.Builder<PresentableItem> builder = ImmutableList.builder();
        for (Item item : cartItems) builder.add(asPresentable(item));
        return builder.build();
    }

    private PresentableItem asPresentable(Item item) {
        Product product = productGateway.findById(item.getProductId());
        return new PresentableItem(item, product);
    }
}
