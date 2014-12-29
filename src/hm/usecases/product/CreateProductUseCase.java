package hm.usecases.product;

import hm.boundaries.delivery.UseCase;
import hm.boundaries.delivery.product.CreateProductResponder;
import hm.boundaries.delivery.product.PersistProductRequest;
import hm.boundaries.persistence.Memory;
import hm.domain.Product;
import hm.usecases.ValidatedUseCase;

public class CreateProductUseCase implements UseCase {
    private Memory<Product> memory;
    private PersistProductRequest request;
    private CreateProductResponder responder;

    public static UseCase create(Memory<Product> memory, PersistProductRequest request, CreateProductResponder responder) {
        UseCase useCase = new CreateProductUseCase(memory, request, responder);
        return new ValidatedUseCase(useCase, new ProductDataValidation(request, responder));
    }

    private CreateProductUseCase(Memory<Product> memory, PersistProductRequest request, CreateProductResponder responder) {
        this.memory = memory;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Product product = Product.create();
        product = product.withName(request.getName());
        product = product.withDescription(request.getDescription());
        product = product.withPictureURI(request.getPictureURI());
        product = product.withPrice(request.getPrice());
        product = memory.persist(product);
        responder.createdWithId(product.getId());
    }
}
