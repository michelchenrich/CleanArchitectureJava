package hm.usecases.sale;

import java.util.List;

public class PresentableSale {
    private List<PresentableItem> items;

    PresentableSale(List<PresentableItem> items) {
        this.items = items;
    }

    public List<PresentableItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (PresentableItem item : items)
            totalPrice += item.getTotalPrice();
        return totalPrice;
    }
}
