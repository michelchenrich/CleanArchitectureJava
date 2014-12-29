package hm.usecases.sale.order;

import hm.boundaries.delivery.IdBasedRequest;
import hm.boundaries.delivery.IdentityResponder;
import hm.boundaries.delivery.UseCase;
import hm.boundaries.persistence.Memory;
import hm.domain.Item;
import hm.domain.Product;
import hm.domain.SaleOrder;
import hm.usecases.IdentityValidation;
import hm.usecases.ValidatedUseCase;

public class CancelSaleOrderUseCase implements UseCase {
    private Memory<SaleOrder> saleOrderMemory;
    private Memory<Product> productMemory;
    private IdBasedRequest request;

    public static UseCase create(Memory<SaleOrder> saleOrderMemory, Memory<Product> productMemory, IdBasedRequest request, IdentityResponder responder) {
        UseCase useCase = new CancelSaleOrderUseCase(saleOrderMemory, productMemory, request);
        return new ValidatedUseCase(useCase, new IdentityValidation(saleOrderMemory, request, responder));
    }

    private CancelSaleOrderUseCase(Memory<SaleOrder> saleOrderMemory, Memory<Product> productMemory, IdBasedRequest request) {
        this.saleOrderMemory = saleOrderMemory;
        this.productMemory = productMemory;
        this.request = request;
    }

    public void execute() {
        SaleOrder order = saleOrderMemory.findById(request.getId());
        restoreUnits(order);
        order = order.asCanceled();
        saleOrderMemory.persist(order);
    }

    private void restoreUnits(SaleOrder order) {
        for (Item item : order.getItems())
            restoreProductUnits(item);
    }

    private void restoreProductUnits(Item item) {
        Product product = productMemory.findById(item.getProductId());
        productMemory.persist(product.withMoreUnits(item.getNumberOfUnits()));
    }
}
