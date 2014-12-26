package hm.usecases.customer;

import hm.usecases.Context;
import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;
import hm.usecases.customer.PresentCustomerResponder.PresentableCustomer;

public class PresentCustomerUseCase implements UseCase {
    private Gateway<Customer> customers;
    private IdBasedRequest request;
    private PresentCustomerResponder responder;

    public static UseCase create(Context context, IdBasedRequest request, PresentCustomerResponder responder) {
        PresentCustomerUseCase useCase = new PresentCustomerUseCase(context, request, responder);
        return new ValidatedUseCase(useCase, responder, new IdentityValidation(request, context.getCustomers()));
    }

    private PresentCustomerUseCase(Context context, IdBasedRequest request, PresentCustomerResponder responder) {
        customers = context.getCustomers();
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Customer customer = customers.findById(request.getId());
        responder.customerFound(makePresentable(customer));
    }

    private PresentableCustomer makePresentable(final Customer customer) {
        return () -> String.format("%s, %s", customer.getLastName(), customer.getFirstName());
    }
}
