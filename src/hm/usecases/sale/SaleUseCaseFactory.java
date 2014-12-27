package hm.usecases.sale;

import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.customer.Customer;
import hm.usecases.product.Product;

public class SaleUseCaseFactory {
    private Gateway<Customer> customerGateway;
    private Gateway<Product> productGateway;

    public SaleUseCaseFactory(Gateway<Customer> customerGateway, Gateway<Product> productGateway) {
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
    }

    public UseCase makeCartAdder(AddProductToCartRequest request, AddProductToCartResponder responder) {
        return AddProductToCartUseCase.create(customerGateway, productGateway, request, responder);
    }

    public UseCase makeCartPresenter(IdBasedRequest request, PresentCustomerCartResponder responder) {
        return PresentCustomerCartUseCase.create(customerGateway, request, responder);
    }
}