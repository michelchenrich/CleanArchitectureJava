package hm.usecases;

import hm.domain.Customer;
import hm.domain.Memory;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.customer.*;

public class CustomerUseCaseFactory {
    private Memory<Customer> memory;

    public CustomerUseCaseFactory(Memory<Customer> memory) {
        this.memory = memory;
    }

    public UseCase makePresenter(IdBasedRequest request, PresentCustomerResponder responder) {
        return PresentCustomerUseCase.create(memory, request, responder);
    }

    public UseCase makeCreator(CustomerPersistenceRequest request, CreateCustomerResponder responder) {
        return CreateCustomerUseCase.create(memory, request, responder);
    }

    public UseCase makeUpdater(UpdateCustomerRequest request, UpdateCustomerResponder responder) {
        return UpdateCustomerUseCase.create(memory, request, responder);
    }
}
