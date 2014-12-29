package hm.usecases.product;

import hm.domain.Memory;
import hm.domain.Product;
import hm.usecases.UseCase;
import hm.usecases.commons.IdentityResponder;
import hm.usecases.commons.IdentityValidation;
import hm.usecases.commons.ValidatedUseCase;

public class AddProductUnitUseCase implements UseCase {
    private Memory<Product> memory;
    private AddProductUnitRequest request;

    public static UseCase create(Memory<Product> memory, AddProductUnitRequest request, IdentityResponder responder) {
        UseCase useCase = new AddProductUnitUseCase(memory, request);
        return new ValidatedUseCase(useCase, new IdentityValidation(memory, request, responder));
    }

    private AddProductUnitUseCase(Memory<Product> memory, AddProductUnitRequest request) {
        this.memory = memory;
        this.request = request;
    }

    public void execute() {
        Product product = memory.findById(request.getId());
        product = product.withMoreUnits(request.getNumberOfUnits());
        memory.persist(product);
    }
}