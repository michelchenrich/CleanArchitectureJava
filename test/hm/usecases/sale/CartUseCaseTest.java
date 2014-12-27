package hm.usecases.sale;

import hm.UseCaseTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CartUseCaseTest extends UseCaseTest {
    protected void assertNotInCart(String customerId, String productId) {
        Cart cart = presentCustomerCart(customerId);
        for (CartItem item : cart.getItems())
            if (item.getProductId().equals(productId))
                fail();
    }

    protected void assertInCart(String customerId, String productId, int numberOfUnits, double price) {
        Cart cart = presentCustomerCart(customerId);
        for (CartItem item : cart.getItems())
            if (item.getProductId().equals(productId) && item.getPrice() == price) {
                assertEquals(numberOfUnits, item.getNumberOfUnits());
                return;
            }
        fail();
    }
}
