package hm.usecases.sale;

import hm.domain.Item;
import hm.domain.Product;
import hm.usecases.product.PresentableProduct;

public class PresentableItem extends PresentableProduct {
    private Item item;

    PresentableItem(Item item, Product product) {
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

    public double getTotalPrice() {
        return getPrice() * getNumberOfUnits();
    }
}
