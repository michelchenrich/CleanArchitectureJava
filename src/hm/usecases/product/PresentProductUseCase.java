package hm.usecases.product;

import hm.usecases.Gateway;
import hm.usecases.UseCase;
import hm.usecases.commons.IdBasedRequest;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;

public class PresentProductUseCase implements UseCase {
    private Gateway<Product> gateway;
    private IdBasedRequest request;
    private PresentProductResponder responder;

    public static UseCase create(Gateway<Product> gateway, IdBasedRequest request, PresentProductResponder responder) {
        UseCase useCase = new PresentProductUseCase(gateway, request, responder);
        return new ValidatedUseCase(useCase, new IdentityValidation(gateway, request, responder));
    }

    private PresentProductUseCase(Gateway<Product> gateway, IdBasedRequest request, PresentProductResponder responder) {
        this.gateway = gateway;
        this.request = request;
        this.responder = responder;
    }

    public void execute() {
        Product product = gateway.findById(request.getId());
        responder.productFound(new PresentableProduct(product));
    }
}
