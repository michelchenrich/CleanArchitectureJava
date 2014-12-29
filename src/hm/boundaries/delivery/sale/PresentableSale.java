package hm.boundaries.delivery.sale;

import java.util.List;

public abstract class PresentableSale {
    public abstract List<PresentableItem> getItems();

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (PresentableItem item : getItems())
            totalPrice += item.getTotalPrice();
        return totalPrice;
    }
}
