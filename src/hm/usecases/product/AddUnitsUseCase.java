package hm.usecases.product;

import hm.boundaries.delivery.IdentityResponder;
import hm.boundaries.delivery.UseCase;
import hm.boundaries.delivery.product.AddUnitsRequest;
import hm.boundaries.persistence.Memory;
import hm.domain.Product;
import hm.usecases.IdentityValidation;
import hm.usecases.ValidatedUseCase;

public class AddUnitsUseCase implements UseCase {
    private Memory<Product> memory;
    private AddUnitsRequest request;

    public static UseCase create(Memory<Product> memory, AddUnitsRequest request, IdentityResponder responder) {
        UseCase useCase = new AddUnitsUseCase(memory, request);
        return new ValidatedUseCase(useCase, new IdentityValidation(memory, request, responder));
    }

    private AddUnitsUseCase(Memory<Product> memory, AddUnitsRequest request) {
        this.memory = memory;
        this.request = request;
    }

    public void execute() {
        Product product = memory.findById(request.getId());
        product = product.withMoreUnits(request.getNumberOfUnits());
        memory.persist(product);
    }
}