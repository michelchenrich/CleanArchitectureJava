package hm.usecases.product;

public class ProductUnit {
    private double price;
    private String productId;

    ProductUnit(String productId, double price) {
        this.price = price;
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }
}
