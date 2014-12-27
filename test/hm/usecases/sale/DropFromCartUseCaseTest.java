package hm.usecases.sale;

import org.junit.Before;
import org.junit.Test;

public class DropFromCartUseCaseTest extends CartUseCaseTest {
    private String customerId;
    private String productId;

    @Before
    public void setUpForSale() {
        customerId = createDefaultCustomer();
        productId = createDefaultProduct();
        addUnitToProduct(productId, 10);
        addProductToCart(customerId, productId, 10);
    }

    @Test
    public void shouldBeGoneFromCartAfterDropping() {
        dropProductFromCart(customerId, productId);
        assertNotInCart(customerId, productId);
    }

    @Test
    public void dropFromNonexistentCustomersCart() {
        dropProductFromCart("nonexistent", productId);
        assertNotFound("nonexistent");
    }

    @Test
    public void dropNonexistentProductFromCart() {
        dropProductFromCart(customerId, "nonexistent");
        assertNotFound("nonexistent");
        assertInCart(customerId, productId, 10, 10.0);
    }
}