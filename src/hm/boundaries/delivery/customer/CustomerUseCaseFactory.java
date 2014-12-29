package hm.boundaries.delivery.customer;

import hm.boundaries.delivery.IdBasedRequest;
import hm.boundaries.delivery.UseCase;
import hm.boundaries.persistence.Memory;
import hm.domain.Customer;
import hm.usecases.customer.CreateCustomerUseCase;
import hm.usecases.customer.PresentCustomerUseCase;
import hm.usecases.customer.UpdateCustomerUseCase;

public class CustomerUseCaseFactory {
    private Memory<Customer> memory;

    public CustomerUseCaseFactory(Memory<Customer> memory) {
        this.memory = memory;
    }

    public UseCase makePresenter(IdBasedRequest request, PresentCustomerResponder responder) {
        return PresentCustomerUseCase.create(memory, request, responder);
    }

    public UseCase makeCreator(PersistCustomerRequest request, CreateCustomerResponder responder) {
        return CreateCustomerUseCase.create(memory, request, responder);
    }

    public UseCase makeUpdater(UpdateCustomerRequest request, UpdateCustomerResponder responder) {
        return UpdateCustomerUseCase.create(memory, request, responder);
    }
}
