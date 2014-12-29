package hm.boundaries.delivery.sale.order;

import hm.boundaries.delivery.sale.PresentableItem;
import hm.boundaries.delivery.sale.PresentableSale;

import java.util.List;

public class PresentableSaleOrder extends PresentableSale {
    private List<PresentableItem> items;
    private boolean canceled;

    public PresentableSaleOrder(List<PresentableItem> items, boolean canceled) {
        this.items = items;
        this.canceled = canceled;
    }

    public List<PresentableItem> getItems() {
        return items;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
