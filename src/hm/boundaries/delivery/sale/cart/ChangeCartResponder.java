package hm.boundaries.delivery.sale.cart;

public interface ChangeCartResponder {
    void invalidCustomerId(String id);
    void invalidProductId(String id);
}
