package hm.usecases.sale.order;

import hm.domain.*;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;

public class SubmitSaleOrderUseCase implements UseCase {
    private Gateway<SaleOrder> saleOrderGateway;
    private Gateway<Customer> customerGateway;
    private IdBasedRequest request;
    private SubmitSaleOrderResponder responder;

    public static UseCase create(Gateway<SaleOrder> saleOrderGateway, Gateway<Customer> customerGateway, IdBasedRequest request, SubmitSaleOrderResponder responder) {
        UseCase useCase = new SubmitSaleOrderUseCase(saleOrderGateway, customerGateway, request, responder);
        return new ValidatedUseCase(useCase, new IdentityValidation(customerGateway, request, responder));
    }

    private SubmitSaleOrderUseCase(Gateway<SaleOrder> saleOrderGateway, Gateway<Customer> customerGateway, IdBasedRequest request, SubmitSaleOrderResponder responder) {
        this.saleOrderGateway = saleOrderGateway;
        this.customerGateway = customerGateway;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Customer customer = customerGateway.findById(request.getId());
        SaleOrder saleOrder = asSaleOrder(customer.getCart());
        saleOrder = saleOrderGateway.persist(saleOrder);
        customerGateway.persist(customer.withEmptyCart());
        responder.createdWithId(saleOrder.getId());
    }

    private SaleOrder asSaleOrder(Cart cart) {
        SaleOrder saleOrder = SaleOrder.create();
        for (Item item : cart.getItems())
            saleOrder = saleOrder.withItem(item);
        return saleOrder;
    }
}
