package hm.boundaries.delivery.sale.cart;

import hm.boundaries.delivery.IdBasedRequest;
import hm.boundaries.delivery.IdentityResponder;
import hm.boundaries.delivery.UseCase;
import hm.boundaries.persistence.Memory;
import hm.domain.Customer;
import hm.domain.Product;
import hm.usecases.sale.cart.AddToCartUseCase;
import hm.usecases.sale.cart.DropCartUseCase;
import hm.usecases.sale.cart.PresentCartUseCase;
import hm.usecases.sale.cart.RemoveFromCartUseCase;

public class CartUseCaseFactory {
    private Memory<Customer> customerMemory;
    private Memory<Product> productMemory;

    public CartUseCaseFactory(Memory<Customer> customerMemory, Memory<Product> productMemory) {
        this.customerMemory = customerMemory;
        this.productMemory = productMemory;
    }

    public UseCase makeAdder(AddToCartRequest request, AddToCartResponder responder) {
        return AddToCartUseCase.create(customerMemory, productMemory, request, responder);
    }

    public UseCase makePresenter(IdBasedRequest request, PresentCartResponder responder) {
        return PresentCartUseCase.create(customerMemory, productMemory, request, responder);
    }

    public UseCase makeRemover(ChangeCartRequest request, IdentityResponder responder) {
        return RemoveFromCartUseCase.create(customerMemory, productMemory, request, responder);
    }

    public UseCase makeDropper(IdBasedRequest request, IdentityResponder responder) {
        return DropCartUseCase.create(customerMemory, productMemory, request, responder);
    }
}
