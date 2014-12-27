package hm.usecases.sale;

import org.junit.Test;

public class DropFromCartUseCaseTest extends CartUseCaseTest {
    @Test
    public void itsSomething() {
        String customerId = createDefaultCustomer();
        String productId = createDefaultProduct();
        addUnitToProduct(productId, 10);
        addProductToCart(customerId, productId, 10);
        dropProductFromCart(customerId, productId);
        assertNotInCart(customerId, productId);
    }
}