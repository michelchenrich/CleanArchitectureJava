package hm.usecases.customer;

import hm.usecases.Context;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;

public class CustomerUseCaseFactory {
    private Context context;

    public CustomerUseCaseFactory(Context context) {
        this.context = context;
    }

    public UseCase makePresenter(IdBasedRequest request, PresentCustomerResponder responder) {
        return PresentCustomerUseCase.create(context, request, responder);
    }

    public UseCase makeCreator(CustomerPersistenceRequest request, CreateCustomerResponder responder) {
        return CreateCustomerUseCase.create(context, request, responder);
    }

    public UseCase makeUpdater(UpdateCustomerRequest request, UpdateCustomerResponder responder) {
        return UpdateCustomerUseCase.create(context, request, responder);
    }
}
