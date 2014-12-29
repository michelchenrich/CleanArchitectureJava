package hm.usecases.product;

import hm.domain.Memory;
import hm.domain.Product;
import hm.usecases.UseCase;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;

public class UpdateProductUseCase implements UseCase {
    private Memory<Product> memory;
    private UpdateProductRequest request;

    public static UseCase create(Memory<Product> memory, UpdateProductRequest request, UpdateProductResponder responder) {
        UseCase useCase = new UpdateProductUseCase(memory, request);
        return new ValidatedUseCase(useCase, new IdentityValidation(memory, request, responder), new ProductDataValidation(request, responder));
    }

    private UpdateProductUseCase(Memory<Product> memory, UpdateProductRequest request) {
        this.memory = memory;
        this.request = request;
    }

    public void execute() {
        Product product = memory.findById(request.getId());
        product = product.withName(request.getName());
        product = product.withDescription(request.getDescription());
        product = product.withPictureURI(request.getPictureURI());
        product = product.withPrice(request.getPrice());
        memory.persist(product);
    }
}
