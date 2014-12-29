package hm.boundaries.delivery.product;

public interface ProductDataResponder {
    void nameIsEmpty();
    void descriptionIsEmpty();
    void pictureURIIsEmpty();
    void priceIsNegative();
}
