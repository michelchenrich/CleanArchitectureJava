package hm.usecases.sale;

import hm.domain.Cart;
import hm.domain.Memory;
import hm.domain.Product;

public class CartPresenter extends SalePresenter<Cart, PresentableSale> {
    public CartPresenter(Memory<Product> productMemory) {
        super(productMemory);
    }

    protected PresentableSale asPresentable(Cart cart) {
        return new PresentableSale(asPresentable(cart.getItems()));
    }
}
