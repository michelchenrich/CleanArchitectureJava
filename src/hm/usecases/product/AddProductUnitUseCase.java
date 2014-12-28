package hm.usecases.product;

import hm.domain.Gateway;
import hm.domain.Product;
import hm.usecases.UseCase;
import hm.usecases.commons.IdentityResponder;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;

public class AddProductUnitUseCase implements UseCase {
    private Gateway<Product> gateway;
    private AddProductUnitRequest request;

    public static UseCase create(Gateway<Product> gateway, AddProductUnitRequest request, IdentityResponder responder) {
        UseCase useCase = new AddProductUnitUseCase(gateway, request);
        return new ValidatedUseCase(useCase, new IdentityValidation(gateway, request, responder));
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