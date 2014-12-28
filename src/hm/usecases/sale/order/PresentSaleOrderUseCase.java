package hm.usecases.sale.order;

import hm.domain.Gateway;
import hm.domain.Product;
import hm.domain.SaleOrder;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;
import hm.usecases.sale.PresentSaleResponder;
import hm.usecases.sale.SalePresenter;

public class PresentSaleOrderUseCase implements UseCase {
    private Gateway<SaleOrder> saleOrderGateway;
    private IdBasedRequest request;
    private PresentSaleResponder responder;
    private SalePresenter presenter;

    public static UseCase create(Gateway<SaleOrder> saleOrderGateway, Gateway<Product> productGateway, IdBasedRequest request, PresentSaleResponder responder) {
        UseCase useCase = new PresentSaleOrderUseCase(saleOrderGateway, productGateway, request, responder);
        return new ValidatedUseCase(useCase, new IdentityValidation(saleOrderGateway, request, responder));
    }

    private PresentSaleOrderUseCase(Gateway<SaleOrder> saleOrderGateway, Gateway<Product> productGateway, IdBasedRequest request, PresentSaleResponder responder) {
        this.saleOrderGateway = saleOrderGateway;
        this.request = request;
        this.responder = responder;
        presenter = new SalePresenter(productGateway);
    }

    public void execute() {
        SaleOrder saleOrder = saleOrderGateway.findById(request.getId());
        this.responder.saleFound(presenter.present(saleOrder));
    }
}
