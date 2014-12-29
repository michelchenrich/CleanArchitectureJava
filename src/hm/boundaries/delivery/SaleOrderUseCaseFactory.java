package hm.boundaries.delivery;

import hm.boundaries.delivery.sale.order.PresentSaleOrderResponder;
import hm.boundaries.delivery.sale.order.SubmitSaleOrderResponder;
import hm.boundaries.persistence.Memory;
import hm.domain.Customer;
import hm.domain.Product;
import hm.domain.SaleOrder;
import hm.usecases.sale.order.CancelSaleOrderUseCase;
import hm.usecases.sale.order.PresentSaleOrderUseCase;
import hm.usecases.sale.order.SubmitSaleOrderUseCase;

public class SaleOrderUseCaseFactory {
    private Memory<Customer> customerMemory;
    private Memory<Product> productMemory;
    private Memory<SaleOrder> saleOrderMemory;

    public SaleOrderUseCaseFactory(Memory<Customer> customerMemory, Memory<Product> productMemory, Memory<SaleOrder> saleOrderMemory) {
        this.customerMemory = customerMemory;
        this.productMemory = productMemory;
        this.saleOrderMemory = saleOrderMemory;
    }

    public UseCase makeOrderSubmitter(IdBasedRequest request, SubmitSaleOrderResponder responder) {
        return SubmitSaleOrderUseCase.create(saleOrderMemory, customerMemory, request, responder);
    }

    public UseCase makeOrderPresenter(IdBasedRequest request, PresentSaleOrderResponder responder) {
        return PresentSaleOrderUseCase.create(saleOrderMemory, productMemory, request, responder);
    }

    public UseCase makeOrderCanceler(IdBasedRequest request, IdentityResponder responder) {
        return CancelSaleOrderUseCase.create(saleOrderMemory, productMemory, request, responder);
    }
}