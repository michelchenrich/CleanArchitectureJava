package hm.usecases.product;

import hm.entities.Gateway;
import hm.entities.Product;
import hm.usecases.UseCase;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;

public class UpdateProductUseCase implements UseCase {
    private Gateway<Product> gateway;
    private UpdateProductRequest request;

    public static UseCase create(Gateway<Product> gateway, UpdateProductRequest request, UpdateProductResponder responder) {
        UseCase useCase = new UpdateProductUseCase(gateway, request);
        return new ValidatedUseCase(useCase, new IdentityValidation(gateway, request, responder), new ProductDataValidation(request, responder));
    }

    private UpdateProductUseCase(Gateway<Product> gateway, UpdateProductRequest request) {
        this.gateway = gateway;
        this.request = request;
    }

    public void execute() {
        Product product = gateway.findById(request.getId());
        product = product.withName(request.getName());
        product = product.withDescription(request.getDescription());
        product = product.withPictureURI(request.getPictureURI());
        product = product.withPrice(request.getPrice());
        gateway.persist(product);
    }
}
