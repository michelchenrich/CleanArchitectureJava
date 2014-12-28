package hm.usecases.saleorder;

import hm.entities.Gateway;
import hm.entities.SaleOrder;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;

public class PresentSaleOrderUseCase implements UseCase {
    private final Gateway<SaleOrder> saleOrderGateway;
    private final IdBasedRequest request;
    private final PresentSaleOrderResponder responder;

    public static UseCase create(Gateway<SaleOrder> saleOrderGateway, IdBasedRequest request, PresentSaleOrderResponder responder) {
        return new PresentSaleOrderUseCase(saleOrderGateway, request, responder);
    }

    private PresentSaleOrderUseCase(Gateway<SaleOrder> saleOrderGateway, IdBasedRequest request, PresentSaleOrderResponder responder) {
        this.saleOrderGateway = saleOrderGateway;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {

    }
}
