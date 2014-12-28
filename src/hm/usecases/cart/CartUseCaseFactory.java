package hm.usecases.cart;

import hm.entities.Customer;
import hm.entities.Gateway;
import hm.entities.Product;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityResponder;

public class CartUseCaseFactory {
    private Gateway<Customer> customerGateway;
    private Gateway<Product> productGateway;

    public CartUseCaseFactory(Gateway<Customer> customerGateway, Gateway<Product> productGateway) {
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
    }

    public UseCase makeProductAdder(AddProductToCartRequest request, AddProductToCartResponder responder) {
        return AddProductToCartUseCase.create(customerGateway, productGateway, request, responder);
    }

    public UseCase makePresenter(IdBasedRequest request, PresentCartResponder responder) {
        return PresentCartUseCase.create(customerGateway, productGateway, request, responder);
    }

    public UseCase makeProductDropper(CartMovementRequest request, IdentityResponder responder) {
        return DropProductFromCartUseCase.create(customerGateway, productGateway, request, responder);
    }

    public UseCase makeDropper(IdBasedRequest request, IdentityResponder responder) {
        return DropAllFromCartUseCase.create(customerGateway, productGateway, request, responder);
    }
}
