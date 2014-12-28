package hm.usecases.sale.order;

import hm.entities.Customer;
import hm.entities.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;

public class SubmitSaleOrderUseCase implements UseCase {
    private Gateway<Customer> gateway;
    private IdBasedRequest request;
    private SubmitSaleOrderResponder responder;

    public static UseCase create(Gateway<Customer> gateway, IdBasedRequest request, SubmitSaleOrderResponder responder) {
        return new SubmitSaleOrderUseCase(gateway, request, responder);
    }

    private SubmitSaleOrderUseCase(Gateway<Customer> gateway, IdBasedRequest request, SubmitSaleOrderResponder responder) {
        this.gateway = gateway;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
    }
}
