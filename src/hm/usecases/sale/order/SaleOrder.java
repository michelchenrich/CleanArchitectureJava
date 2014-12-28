package hm.usecases.sale.order;

import hm.entities.Identifiable;

public class SaleOrder implements Identifiable {
    private double totalPrice = 750.0;

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getId() {
        return null;
    }

    public Identifiable withId(String id) {
        return null;
    }
}
