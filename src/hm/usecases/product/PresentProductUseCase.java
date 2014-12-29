package hm.usecases.product;

import hm.boundaries.delivery.IdBasedRequest;
import hm.boundaries.delivery.UseCase;
import hm.boundaries.delivery.product.PresentProductResponder;
import hm.boundaries.delivery.product.PresentableProduct;
import hm.boundaries.persistence.Memory;
import hm.domain.Product;
import hm.usecases.IdentityValidation;
import hm.usecases.ValidatedUseCase;

public class PresentProductUseCase implements UseCase {
    private Memory<Product> memory;
    private IdBasedRequest request;
    private PresentProductResponder responder;

    public static UseCase create(Memory<Product> memory, IdBasedRequest request, PresentProductResponder responder) {
        UseCase useCase = new PresentProductUseCase(memory, request, responder);
        return new ValidatedUseCase(useCase, new IdentityValidation(memory, request, responder));
    }

    private PresentProductUseCase(Memory<Product> memory, IdBasedRequest request, PresentProductResponder responder) {
        this.memory = memory;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Product product = memory.findById(request.getId());
        responder.productFound(new PresentableProduct(product));
    }
}
