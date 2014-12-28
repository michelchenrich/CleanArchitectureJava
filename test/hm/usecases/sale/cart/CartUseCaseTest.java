package hm.usecases.sale.cart;

import hm.usecases.UseCaseTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CartUseCaseTest extends UseCaseTest {
    protected void assertNotInCart(PresentableCart cart, String productId) {
        for (PresentableItem item : cart.getItems())
            if (item.getProductId().equals(productId))
                fail();
    }

    protected void assertInCart(PresentableCart cart, String productId, String name, String description, String pictureURI, int numberOfUnits, double price) {
        for (PresentableItem item : cart.getItems())
            if (item.getProductId().equals(productId)) {
                assertEquals(name, item.getName());
                assertEquals(description, item.getDescription());
                assertEquals(pictureURI, item.getPictureURI());
                assertEquals(numberOfUnits, item.getNumberOfUnits());
                assertEquals(price, item.getPrice(), .001);
                return;
            }
        fail();
    }
}
