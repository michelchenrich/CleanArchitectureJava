package hm.usecases.cart;

public interface AddProductToCartRequest extends CartMovementRequest {
    int getNumberOfUnits();
}
