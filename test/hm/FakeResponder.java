package hm;

import hm.usecases.customer.CreateCustomerResponder;
import hm.usecases.customer.PresentCustomerResponder;
import hm.usecases.customer.UpdateCustomerResponder;

class FakeResponder implements PresentCustomerResponder, CreateCustomerResponder, UpdateCustomerResponder {
    public boolean firstNameIsEmpty;
    public boolean lastNameIsEmpty;
    public boolean customerNotFound;
    public String createdWithId;
    public PresentableCustomer presentableCustomer;

    public void invalidId(String id) {
        customerNotFound = true;
    }

    public void createdWithId(String id) {
        createdWithId = id;
    }

    public void firstNameIsEmpty() {
        firstNameIsEmpty = true;
    }

    public void lastNameIsEmpty() {
        lastNameIsEmpty = true;
    }

    public void customerFound(PresentableCustomer presentableCustomer) {
        this.presentableCustomer = presentableCustomer;
    }
}
