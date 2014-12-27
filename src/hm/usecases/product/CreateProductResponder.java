package hm.usecases.product;

public interface CreateProductResponder extends ProductDataResponder {
    void createdWithId(String id);
}
