package hm.usecases;

import hm.domain.Customer;
import hm.domain.Gateway;
import hm.domain.Product;
import hm.domain.SaleOrder;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.sale.PresentSaleResponder;
import hm.usecases.sale.order.PresentSaleOrderUseCase;
import hm.usecases.sale.order.SubmitSaleOrderResponder;
import hm.usecases.sale.order.SubmitSaleOrderUseCase;

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
        return SubmitSaleOrderUseCase.create(saleOrderGateway, customerGateway, request, responder);
    }

    public UseCase makeOrderPresenter(IdBasedRequest request, PresentSaleResponder responder) {
        return PresentSaleOrderUseCase.create(saleOrderGateway, productGateway, request, responder);
    }
}