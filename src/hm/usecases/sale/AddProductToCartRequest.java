package hm.usecases.sale;

public interface AddProductToCartRequest extends CartMovementRequest {
    int getNumberOfUnits();
}
