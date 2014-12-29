package hm.usecases.product;

import hm.domain.Memory;
import hm.domain.Product;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;

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
