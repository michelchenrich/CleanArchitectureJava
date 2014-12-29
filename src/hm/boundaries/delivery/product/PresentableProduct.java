package hm.boundaries.delivery.product;

import hm.domain.Product;

public class PresentableProduct {
    private Product product;

    public PresentableProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return product.getName();
    }

    public String getDescription() {
        return product.getDescription();
    }

    public String getPictureURI() {
        return product.getPictureURI();
    }

    public int getNumberOfUnits() {
        return product.getNumberOfUnits();
    }

    public double getPrice() {
        return product.getPrice();
    }
}
