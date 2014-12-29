package hm.usecases;

import hm.domain.Memory;
import hm.domain.Product;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityResponder;
import hm.usecases.product.*;

public class ProductUseCaseFactory {
    private Memory<Product> memory;

    public ProductUseCaseFactory(Memory<Product> memory) {
        this.memory = memory;
    }

    public UseCase makeCreator(PersistProductRequest request, CreateProductResponder responder) {
        return CreateProductUseCase.create(memory, request, responder);
    }

    public UseCase makePresenter(IdBasedRequest request, PresentProductResponder responder) {
        return PresentProductUseCase.create(memory, request, responder);
    }

    public UseCase makeUnitAdder(AddProductUnitRequest request, IdentityResponder responder) {
        return AddProductUnitUseCase.create(memory, request, responder);
    }

    public UseCase makeUpdater(UpdateProductRequest request, UpdateProductResponder responder) {
        return UpdateProductUseCase.create(memory, request, responder);
    }
}
