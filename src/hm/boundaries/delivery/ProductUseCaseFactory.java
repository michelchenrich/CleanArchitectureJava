package hm.boundaries.delivery;

import hm.boundaries.delivery.product.*;
import hm.boundaries.persistence.Memory;
import hm.domain.Product;
import hm.usecases.product.AddUnitsUseCase;
import hm.usecases.product.CreateProductUseCase;
import hm.usecases.product.PresentProductUseCase;
import hm.usecases.product.UpdateProductUseCase;

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

    public UseCase makeUnitAdder(AddUnitsRequest request, IdentityResponder responder) {
        return AddUnitsUseCase.create(memory, request, responder);
    }

    public UseCase makeUpdater(UpdateProductRequest request, UpdateProductResponder responder) {
        return UpdateProductUseCase.create(memory, request, responder);
    }
}
