package hm.usecases.saleorder;

import hm.entities.Customer;
import hm.entities.Gateway;
import hm.entities.Product;
import hm.entities.SaleOrder;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;

public class SaleOrderUseCaseFactory {
    private Gateway<Customer> customerGateway;
    private Gateway<Product> productGateway;
    private Gateway<SaleOrder> saleOrderGateway;

    public SaleOrderUseCaseFactory(Gateway<Customer> customerGateway, Gateway<Product> productGateway, Gateway<SaleOrder> saleOrderGateway) {
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
        this.saleOrderGateway = saleOrderGateway;
    }

    public UseCase makeOrderSubmitter(IdBasedRequest request, SubmitSaleOrderResponder responder) {
        return SubmitSaleOrderUseCase.create(customerGateway, request, responder);
    }

    public UseCase makeOrderPresenter(IdBasedRequest request, PresentSaleOrderResponder responder) {
        return PresentSaleOrderUseCase.create(saleOrderGateway, request, responder);
    }
}