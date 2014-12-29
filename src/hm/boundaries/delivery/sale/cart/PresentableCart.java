package hm.boundaries.delivery.sale.cart;

import hm.boundaries.delivery.sale.PresentableItem;
import hm.boundaries.delivery.sale.PresentableSale;

import java.util.List;

public class PresentableCart extends PresentableSale {
    private List<PresentableItem> items;

    public PresentableCart(List<PresentableItem> items) {
        this.items = items;
    }

    public List<PresentableItem> getItems() {
        return items;
    }
}
