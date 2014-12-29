package hm.usecases;

import hm.domain.Customer;
import hm.domain.Memory;
import hm.domain.Product;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityResponder;
import hm.usecases.sale.cart.*;

public class CartUseCaseFactory {
    private Memory<Customer> customerMemory;
    private Memory<Product> productMemory;

    public CartUseCaseFactory(Memory<Customer> customerMemory, Memory<Product> productMemory) {
        this.customerMemory = customerMemory;
        this.productMemory = productMemory;
    }

    public UseCase makeProductAdder(AddProductToCartRequest request, AddProductToCartResponder responder) {
        return AddProductToCartUseCase.create(customerMemory, productMemory, request, responder);
    }

    public UseCase makePresenter(IdBasedRequest request, PresentCartResponder responder) {
        return PresentCartUseCase.create(customerMemory, productMemory, request, responder);
    }

    public UseCase makeProductDropper(CartMovementRequest request, IdentityResponder responder) {
        return DropProductFromCartUseCase.create(customerMemory, productMemory, request, responder);
    }

    public UseCase makeDropper(IdBasedRequest request, IdentityResponder responder) {
        return DropAllFromCartUseCase.create(customerMemory, productMemory, request, responder);
    }
}
