package hm.usecases.sale.order;

import hm.usecases.sale.PresentableItem;
import hm.usecases.sale.PresentableSale;

import java.util.List;

public class PresentableSaleOrder extends PresentableSale {
    private boolean canceled;

    PresentableSaleOrder(List<PresentableItem> items, boolean canceled) {
        super(items);
        this.canceled = canceled;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
