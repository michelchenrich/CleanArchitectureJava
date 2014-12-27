package hm.usecases.sale;

import hm.usecases.product.Product;

public class CartItem {
    private String productId;
    private int numberOfUnits;
    private double price;

    CartItem(Product product, int numberOfUnits) {
        this(product.getId(), product.getPrice(), numberOfUnits);
    }

    CartItem(String productId, double price, int numberOfUnits) {
        this.productId = productId;
        this.price = price;
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

    public CartItem withMoreUnits(int numberOfUnits) {
        return new CartItem(productId, price, this.numberOfUnits + numberOfUnits);
    }

}
