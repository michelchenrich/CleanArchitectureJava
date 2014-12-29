package hm.usecases.sale.cart;

import hm.boundaries.delivery.IdBasedRequest;
import hm.boundaries.delivery.UseCase;
import hm.boundaries.delivery.sale.cart.PresentCartResponder;
import hm.boundaries.persistence.Memory;
import hm.domain.Customer;
import hm.domain.Product;
import hm.usecases.IdentityValidation;
import hm.usecases.ValidatedUseCase;

public class PresentCartUseCase implements UseCase {
    private Memory<Customer> customerMemory;
    private IdBasedRequest request;
    private PresentCartResponder responder;
    private CartPresenter presenter;

    public static UseCase create(Memory<Customer> customerMemory, Memory<Product> productMemory, IdBasedRequest request, PresentCartResponder responder) {
        UseCase useCase = new PresentCartUseCase(customerMemory, productMemory, request, responder);
        return new ValidatedUseCase(useCase, new IdentityValidation(customerMemory, request, responder));
    }

    private PresentCartUseCase(Memory<Customer> customerMemory, Memory<Product> productMemory, IdBasedRequest request, PresentCartResponder responder) {
        this.customerMemory = customerMemory;
        this.request = request;
        this.responder = responder;
        presenter = new CartPresenter(productMemory);
    }

    public void execute() {
        Customer customer = customerMemory.findById(request.getId());
        responder.cartFound(presenter.present(customer.getCart()));
    }
}
