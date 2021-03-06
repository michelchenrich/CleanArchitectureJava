package hm.boundaries.delivery.product;

import hm.boundaries.delivery.IdBasedRequest;
import hm.boundaries.delivery.IdentityResponder;
import hm.boundaries.delivery.UseCase;
import hm.boundaries.persistence.Memory;
import hm.domain.Product;
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

    public UseCase makeListPresenter(PresentProductsResponder responder) {
        return PresentProductsUseCase.create(memory, responder);
    }

    public UseCase makeUnitAdder(AddUnitsRequest request, IdentityResponder responder) {
        return AddUnitsUseCase.create(memory, request, responder);
    }

    public UseCase makeUpdater(UpdateProductRequest request, UpdateProductResponder responder) {
        return UpdateProductUseCase.create(memory, request, responder);
    }
}
