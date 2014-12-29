package hm.usecases.sale.order;

import hm.domain.Item;
import hm.domain.Memory;
import hm.domain.Product;
import hm.domain.SaleOrder;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityResponder;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;

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
