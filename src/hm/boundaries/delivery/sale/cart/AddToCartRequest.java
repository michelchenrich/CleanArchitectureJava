package hm.boundaries.delivery.sale.cart;

public interface AddToCartRequest extends ChangeCartRequest {
    int getNumberOfUnits();
}
