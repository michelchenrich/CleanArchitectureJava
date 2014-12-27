package hm.usecases.product;

import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.ValidatedUseCase;

public class CreateProductUseCase implements UseCase {
    private Gateway<Product> gateway;
    private PersistProductRequest request;
    private CreateProductResponder responder;

    public static UseCase create(Gateway<Product> gateway, PersistProductRequest request, CreateProductResponder responder) {
        UseCase useCase = new CreateProductUseCase(gateway, request, responder);
        return new ValidatedUseCase(useCase, new ProductDataValidation(request, responder));
    }

    private CreateProductUseCase(Gateway<Product> gateway, PersistProductRequest request, CreateProductResponder responder) {
        this.gateway = gateway;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Product product = Product.create();
        product = product.withName(request.getName());
        product = product.withDescription(request.getDescription());
        product = product.withPictureURI(request.getPictureURI());
        product = product.withPrice(request.getPrice());
        product = gateway.persist(product);
        responder.createdWithId(product.getId());
    }

}
