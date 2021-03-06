package hm.domain;

public class Item {
    private String productId;
    private int numberOfUnits;
    private double price;

    Item(String productId, double price, int numberOfUnits) {
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

    public Item withMoreUnits(int numberOfUnits) {
        return new Item(productId, price, this.numberOfUnits + numberOfUnits);
    }
}
