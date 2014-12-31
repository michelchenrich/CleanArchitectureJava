package hm.usecases.product;

import com.google.common.collect.ImmutableList;
import hm.boundaries.delivery.UseCase;
import hm.boundaries.delivery.product.PresentProductsResponder;
import hm.boundaries.delivery.product.PresentableProduct;
import hm.boundaries.persistence.Memory;
import hm.domain.Product;

import java.util.List;

public class PresentProductsUseCase implements UseCase {
    private Memory<Product> memory;
    private PresentProductsResponder responder;

    public static UseCase create(Memory<Product> memory, PresentProductsResponder responder) {
        return new PresentProductsUseCase(memory, responder);
    }

    private PresentProductsUseCase(Memory<Product> memory, PresentProductsResponder responder) {
        this.memory = memory;
        this.responder = responder;
    }

    public void execute() {
        responder.productsFound(asPresentable(memory.findAll()));
    }

    private List<PresentableProduct> asPresentable(List<Product> products) {
        ImmutableList.Builder<PresentableProduct> builder = ImmutableList.builder();
        for (Product product : products)
            builder.add(new PresentableProduct(product));
        return builder.build();
    }
}