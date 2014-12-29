package hm.boundaries.delivery.product;

import hm.boundaries.delivery.IdentityResponder;

public interface PresentProductResponder extends IdentityResponder {
    void productFound(PresentableProduct product);
}
