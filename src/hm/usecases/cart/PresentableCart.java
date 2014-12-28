package hm.usecases.cart;

import java.util.List;

public class PresentableCart {
    private List<PresentableItem> items;

    public PresentableCart(List<PresentableItem> items) {
        this.items = items;
    }

    public List<PresentableItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (PresentableItem item : items) totalPrice += item.getTotalPrice();
        return totalPrice;
    }
}
