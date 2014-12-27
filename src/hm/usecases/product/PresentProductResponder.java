package hm.usecases.product;

import hm.usecases.commons.IdentityResponder;

public interface PresentProductResponder extends IdentityResponder {
    void productFound(Product product);
}
