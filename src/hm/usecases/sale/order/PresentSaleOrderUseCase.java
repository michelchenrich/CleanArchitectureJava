package hm.usecases.sale.order;

import hm.boundaries.delivery.IdBasedRequest;
import hm.boundaries.delivery.UseCase;
import hm.boundaries.delivery.sale.order.PresentSaleOrderResponder;
import hm.boundaries.persistence.Memory;
import hm.domain.Product;
import hm.domain.SaleOrder;
import hm.usecases.IdentityValidation;
import hm.usecases.ValidatedUseCase;

public class PresentSaleOrderUseCase implements UseCase {
    private Memory<SaleOrder> saleOrderMemory;
    private IdBasedRequest request;
    private PresentSaleOrderResponder responder;
    private SaleOrderPresenter presenter;

    public static UseCase create(Memory<SaleOrder> saleOrderMemory, Memory<Product> productMemory, IdBasedRequest request, PresentSaleOrderResponder responder) {
        UseCase useCase = new PresentSaleOrderUseCase(saleOrderMemory, productMemory, request, responder);
        return new ValidatedUseCase(useCase, new IdentityValidation(saleOrderMemory, request, responder));
    }

    private PresentSaleOrderUseCase(Memory<SaleOrder> saleOrderMemory, Memory<Product> productMemory, IdBasedRequest request, PresentSaleOrderResponder responder) {
        this.saleOrderMemory = saleOrderMemory;
        this.request = request;
        this.responder = responder;
        presenter = new SaleOrderPresenter(productMemory);
    }

    public void execute() {
        SaleOrder saleOrder = saleOrderMemory.findById(request.getId());
        this.responder.saleOrderFound(presenter.present(saleOrder));
    }
}
