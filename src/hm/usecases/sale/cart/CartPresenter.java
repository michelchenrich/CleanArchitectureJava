package hm.usecases.sale.cart;

import hm.boundaries.delivery.sale.cart.PresentableCart;
import hm.boundaries.persistence.Memory;
import hm.domain.Cart;
import hm.domain.Product;
import hm.usecases.sale.SalePresenter;

public class CartPresenter extends SalePresenter<Cart, PresentableCart> {
    public CartPresenter(Memory<Product> productMemory) {
        super(productMemory);
    }

    protected PresentableCart asPresentable(Cart cart) {
        return new PresentableCart(asPresentable(cart.getItems()));
    }
}
