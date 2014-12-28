package hm.usecases;

import hm.domain.Gateway;
import hm.domain.Product;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.product.*;

public class ProductUseCaseFactory {
    private Gateway<Product> gateway;

    public ProductUseCaseFactory(Gateway<Product> gateway) {
        this.gateway = gateway;
    }

    public UseCase makeCreator(PersistProductRequest request, CreateProductResponder responder) {
        return CreateProductUseCase.create(gateway, request, responder);
    }

    public UseCase makePresenter(IdBasedRequest request, PresentProductResponder responder) {
        return PresentProductUseCase.create(gateway, request, responder);
    }

    public UseCase makeUnitAdder(AddProductUnitRequest request, AddProductUnitResponder responder) {
        return AddProductUnitUseCase.create(gateway, request, responder);
    }

    public UseCase makeUpdater(UpdateProductRequest request, UpdateProductResponder responder) {
        return UpdateProductUseCase.create(gateway, request, responder);
    }
}
