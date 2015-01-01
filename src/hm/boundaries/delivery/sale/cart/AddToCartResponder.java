package hm.boundaries.delivery.sale.cart;

public interface AddToCartResponder extends ChangeCartResponder {
    void numberOfUnitsIsLessThanOne();
}
