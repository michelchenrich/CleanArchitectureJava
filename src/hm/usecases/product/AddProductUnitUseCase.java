package hm.usecases.product;

import hm.entities.Gateway;
import hm.entities.Product;
import hm.usecases.UseCase;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;

public class AddProductUnitUseCase implements UseCase {
    private Gateway<Product> gateway;
    private AddProductUnitRequest request;

    public static UseCase create(Gateway<Product> gateway, AddProductUnitRequest request, AddProductUnitResponder responder) {
        UseCase useCase = new AddProductUnitUseCase(gateway, request);
        return new ValidatedUseCase(useCase, new IdentityValidation(gateway, request, responder), new UnitPriceValidation(request, responder));
    }

    private AddProductUnitUseCase(Gateway<Product> gateway, AddProductUnitRequest request) {
        this.gateway = gateway;
        this.request = request;
    }

    public void execute() {
        Product product = gateway.findById(request.getId());
        product = product.withMoreUnits(request.getNumberOfUnits());
        gateway.persist(product);
    }

}