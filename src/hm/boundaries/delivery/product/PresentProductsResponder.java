package hm.boundaries.delivery.product;

import java.util.List;

public interface PresentProductsResponder {
    void productsFound(List<PresentableProduct> products);
}
