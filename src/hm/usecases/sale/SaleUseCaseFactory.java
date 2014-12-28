package hm.usecases.sale;

import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityResponder;
import hm.usecases.customer.Customer;
import hm.usecases.product.Product;
import hm.usecases.sale.cart.*;
import hm.usecases.sale.order.*;

public class SaleUseCaseFactory {
    private Gateway<Customer> customerGateway;
    private Gateway<Product> productGateway;
    private Gateway<SaleOrder> saleOrderGateway;

    public SaleUseCaseFactory(Gateway<Customer> customerGateway, Gateway<Product> productGateway) {
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
    }

    public UseCase makeCartProductAdder(AddProductToCartRequest request, AddProductToCartResponder responder) {
        return AddProductToCartUseCase.create(customerGateway, productGateway, request, responder);
    }

    public UseCase makeCartPresenter(IdBasedRequest request, PresentCartResponder responder) {
        return PresentCartUseCase.create(customerGateway, productGateway, request, responder);
    }

    public UseCase makeCartProductDropper(CartMovementRequest request, IdentityResponder responder) {
        return DropProductFromCartUseCase.create(customerGateway, productGateway, request, responder);
    }

    public UseCase makeCartDropper(IdBasedRequest request, IdentityResponder responder) {
        return DropAllFromCartUseCase.create(customerGateway, productGateway, request, responder);
    }

    public UseCase makeOrderSubmitter(IdBasedRequest request, SubmitSaleOrderResponder responder) {
        return SubmitSaleOrderUseCase.create(customerGateway, request, responder);
    }

    public UseCase makeOrderPresenter(IdBasedRequest request, PresentSaleOrderResponder responder) {
        return PresentSaleOrderUseCase.create(saleOrderGateway, request, responder);
    }
}
