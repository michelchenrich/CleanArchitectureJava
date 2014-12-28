package hm.usecases.sale;

import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityResponder;
import hm.usecases.customer.Customer;
import hm.usecases.product.Product;

public class SaleUseCaseFactory {
    private Gateway<Customer> customerGateway;
    private Gateway<Product> productGateway;

    public SaleUseCaseFactory(Gateway<Customer> customerGateway, Gateway<Product> productGateway) {
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
    }

    public UseCase makeCartProductAdder(AddProductToCartRequest request, AddProductToCartResponder responder) {
        return AddProductToCartUseCase.create(customerGateway, productGateway, request, responder);
    }

    public UseCase makeCartPresenter(IdBasedRequest request, PresentCustomerCartResponder responder) {
        return PresentCustomerCartUseCase.create(customerGateway, request, responder);
    }

    public UseCase makeCartProductDrooper(CartMovementRequest request, IdentityResponder responder) {
        return DropProductFromCartUseCase.create(customerGateway, productGateway, request, responder);
    }

    public UseCase makeCartDrooper(IdBasedRequest request, IdentityResponder responder) {
        return DropAllFromCartUseCase.create(customerGateway, productGateway, request, responder);
    }
}
