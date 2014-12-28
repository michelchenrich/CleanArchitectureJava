package hm.usecases.sale.cart;

import hm.entities.Item;
import hm.entities.Product;
import hm.usecases.product.PresentableProduct;

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
