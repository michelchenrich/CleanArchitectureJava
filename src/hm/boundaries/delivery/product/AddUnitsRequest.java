package hm.boundaries.delivery.product;

import hm.boundaries.delivery.IdBasedRequest;

public interface AddUnitsRequest extends IdBasedRequest {
    int getNumberOfUnits();
}
