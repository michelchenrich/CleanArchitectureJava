package hm.usecases;

import hm.domain.Customer;
import hm.domain.Gateway;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.customer.*;

public class CustomerUseCaseFactory {
    private Gateway<Customer> gateway;

    public CustomerUseCaseFactory(Gateway<Customer> gateway) {
        this.gateway = gateway;
    }

    public UseCase makePresenter(IdBasedRequest request, PresentCustomerResponder responder) {
        return PresentCustomerUseCase.create(gateway, request, responder);
    }

    public UseCase makeCreator(CustomerPersistenceRequest request, CreateCustomerResponder responder) {
        return CreateCustomerUseCase.create(gateway, request, responder);
    }

    public UseCase makeUpdater(UpdateCustomerRequest request, UpdateCustomerResponder responder) {
        return UpdateCustomerUseCase.create(gateway, request, responder);
    }
}
