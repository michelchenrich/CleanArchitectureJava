package hm.usecases.sale;

import hm.UseCaseTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CartUseCaseTest extends UseCaseTest {
    protected void assertNotInCart(String customerId, String productId) {
        for (Item item : presentCustomerCart(customerId))
            if (item.getProductId().equals(productId))
                fail();
    }

    protected void assertInCart(String customerId, String productId, int numberOfUnits, double price) {
        for (Item item : presentCustomerCart(customerId))
            if (item.getProductId().equals(productId) && item.getPrice() == price) {
                assertEquals(numberOfUnits, item.getNumberOfUnits());
                return;
            }
        fail();
    }
}
