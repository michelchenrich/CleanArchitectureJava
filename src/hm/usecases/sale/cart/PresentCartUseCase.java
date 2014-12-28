package hm.usecases.sale.cart;

import hm.domain.Customer;
import hm.domain.Gateway;
import hm.domain.Product;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;
import hm.usecases.sale.PresentSaleResponder;
import hm.usecases.sale.SalePresenter;

public class PresentCartUseCase implements UseCase {
    private Gateway<Customer> customerGateway;
    private IdBasedRequest request;
    private PresentSaleResponder responder;
    private SalePresenter presenter;

    public static UseCase create(Gateway<Customer> customerGateway, Gateway<Product> productGateway, IdBasedRequest request, PresentSaleResponder responder) {
        UseCase useCase = new PresentCartUseCase(customerGateway, productGateway, request, responder);
        return new ValidatedUseCase(useCase, new IdentityValidation(customerGateway, request, responder));
    }

    private PresentCartUseCase(Gateway<Customer> customerGateway, Gateway<Product> productGateway, IdBasedRequest request, PresentSaleResponder responder) {
        this.customerGateway = customerGateway;
        this.request = request;
        this.responder = responder;
        presenter = new SalePresenter(productGateway);
    }

    public void execute() {
        Customer customer = customerGateway.findById(request.getId());
        responder.saleFound(presenter.present(customer.getCart()));
    }
}
