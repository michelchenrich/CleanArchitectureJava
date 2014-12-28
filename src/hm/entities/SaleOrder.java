package hm.entities;

public class SaleOrder implements Identifiable<SaleOrder> {
    private double totalPrice = 750.0;

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getId() {
        return null;
    }

    public SaleOrder withId(String id) {
        return null;
    }
}
