package hm.usecases.sale;

import hm.usecases.commons.IdentityResponder;

import java.util.List;

public interface PresentCustomerCartResponder extends IdentityResponder {
    void cartFound(List<Item> items);
}
