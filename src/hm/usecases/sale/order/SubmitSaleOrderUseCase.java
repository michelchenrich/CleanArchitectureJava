package hm.usecases.sale.order;

import hm.boundaries.delivery.IdBasedRequest;
import hm.boundaries.delivery.UseCase;
import hm.boundaries.delivery.sale.order.SubmitSaleOrderResponder;
import hm.boundaries.persistence.Memory;
import hm.domain.Cart;
import hm.domain.Customer;
import hm.domain.Item;
import hm.domain.SaleOrder;
import hm.usecases.IdentityValidation;
import hm.usecases.ValidatedUseCase;

public class SubmitSaleOrderUseCase implements UseCase {
    private Memory<SaleOrder> saleOrderMemory;
    private Memory<Customer> customerMemory;
    private IdBasedRequest request;
    private SubmitSaleOrderResponder responder;

    public static UseCase create(Memory<SaleOrder> saleOrderMemory, Memory<Customer> customerMemory, IdBasedRequest request, SubmitSaleOrderResponder responder) {
        UseCase useCase = new SubmitSaleOrderUseCase(saleOrderMemory, customerMemory, request, responder);
        return new ValidatedUseCase(useCase, new IdentityValidation(customerMemory, request, responder));
    }

    private SubmitSaleOrderUseCase(Memory<SaleOrder> saleOrderMemory, Memory<Customer> customerMemory, IdBasedRequest request, SubmitSaleOrderResponder responder) {
        this.saleOrderMemory = saleOrderMemory;
        this.customerMemory = customerMemory;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Customer customer = customerMemory.findById(request.getId());
        SaleOrder saleOrder = asSaleOrder(customer.getCart());
        saleOrder = saleOrderMemory.persist(saleOrder);
        customerMemory.persist(customer.withEmptyCart());
        responder.createdWithId(saleOrder.getId());
    }

    private SaleOrder asSaleOrder(Cart cart) {
        SaleOrder saleOrder = SaleOrder.create();
        for (Item item : cart.getItems())
            saleOrder = saleOrder.withItem(item);
        return saleOrder;
    }
}
