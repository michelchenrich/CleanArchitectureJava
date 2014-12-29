package hm.usecases.sale.cart;

import hm.domain.Customer;
import hm.domain.Memory;
import hm.domain.Product;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;
import hm.usecases.sale.PresentSaleResponder;
import hm.usecases.sale.SalePresenter;

public class PresentCartUseCase implements UseCase {
    private Memory<Customer> customerMemory;
    private IdBasedRequest request;
    private PresentSaleResponder responder;
    private SalePresenter presenter;

    public static UseCase create(Memory<Customer> customerMemory, Memory<Product> productMemory, IdBasedRequest request, PresentSaleResponder responder) {
        UseCase useCase = new PresentCartUseCase(customerMemory, productMemory, request, responder);
        return new ValidatedUseCase(useCase, new IdentityValidation(customerMemory, request, responder));
    }

    private PresentCartUseCase(Memory<Customer> customerMemory, Memory<Product> productMemory, IdBasedRequest request, PresentSaleResponder responder) {
        this.customerMemory = customerMemory;
        this.request = request;
        this.responder = responder;
        presenter = new SalePresenter(productMemory);
    }

    public void execute() {
        Customer customer = customerMemory.findById(request.getId());
        responder.saleFound(presenter.present(customer.getCart()));
    }
}
