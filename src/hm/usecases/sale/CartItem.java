package hm.usecases.sale;

import hm.usecases.product.Product;

public class CartItem {
    private String productId;
    private int numberOfUnits;
    private double price;

    CartItem(Product product, int numberOfUnits) {
        this.productId = product.getId();
        this.price = product.getPrice();
        this.numberOfUnits = numberOfUnits;
    }

    public String getProductId() {
        return productId;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public double getPrice() {
        return price;
    }
}
