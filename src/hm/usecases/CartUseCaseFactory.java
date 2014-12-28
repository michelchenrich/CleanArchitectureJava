package hm.usecases;

import hm.domain.Customer;
import hm.domain.Gateway;
import hm.domain.Product;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityResponder;
import hm.usecases.sale.PresentSaleResponder;
import hm.usecases.sale.cart.*;

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

    public UseCase makePresenter(IdBasedRequest request, PresentSaleResponder responder) {
        return PresentCartUseCase.create(customerGateway, productGateway, request, responder);
    }

    public UseCase makeProductDropper(CartMovementRequest request, IdentityResponder responder) {
        return DropProductFromCartUseCase.create(customerGateway, productGateway, request, responder);
    }

    public UseCase makeDropper(IdBasedRequest request, IdentityResponder responder) {
        return DropAllFromCartUseCase.create(customerGateway, productGateway, request, responder);
    }
}
