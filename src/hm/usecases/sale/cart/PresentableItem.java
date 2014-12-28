package hm.usecases.sale.cart;

import hm.usecases.product.PresentableProduct;
import hm.usecases.product.Product;

public class PresentableItem extends PresentableProduct {
    private Item item;

    public PresentableItem(Item item, Product product) {
        super(product);
        this.item = item;
    }

    public String getProductId() {
        return item.getProductId();
    }

    public int getNumberOfUnits() {
        return item.getNumberOfUnits();
    }

    public double getPrice() {
        return item.getPrice();
    }
}
