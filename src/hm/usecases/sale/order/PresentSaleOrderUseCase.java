package hm.usecases.sale.order;

import hm.domain.Memory;
import hm.domain.Product;
import hm.domain.SaleOrder;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;
import hm.usecases.sale.PresentSaleResponder;
import hm.usecases.sale.SalePresenter;

public class PresentSaleOrderUseCase implements UseCase {
    private Memory<SaleOrder> saleOrderMemory;
    private IdBasedRequest request;
    private PresentSaleResponder responder;
    private SalePresenter presenter;

    public static UseCase create(Memory<SaleOrder> saleOrderMemory, Memory<Product> productMemory, IdBasedRequest request, PresentSaleResponder responder) {
        UseCase useCase = new PresentSaleOrderUseCase(saleOrderMemory, productMemory, request, responder);
        return new ValidatedUseCase(useCase, new IdentityValidation(saleOrderMemory, request, responder));
    }

    private PresentSaleOrderUseCase(Memory<SaleOrder> saleOrderMemory, Memory<Product> productMemory, IdBasedRequest request, PresentSaleResponder responder) {
        this.saleOrderMemory = saleOrderMemory;
        this.request = request;
        this.responder = responder;
        presenter = new SalePresenter(productMemory);
    }

    public void execute() {
        SaleOrder saleOrder = saleOrderMemory.findById(request.getId());
        this.responder.saleFound(presenter.present(saleOrder));
    }
}
