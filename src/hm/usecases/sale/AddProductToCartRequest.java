package hm.usecases.sale;

public interface AddProductToCartRequest {
    String getCustomerId();

    String getProductId();

    int getNumberOfUnits();
}
