package hm.usecases.sale.cart;

public interface AddProductToCartRequest extends CartMovementRequest {
    int getNumberOfUnits();
}
