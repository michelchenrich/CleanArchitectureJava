package hm.usecases.cart;

import hm.usecases.UseCaseTest;
import hm.usecases.product.PresentableProduct;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CartUseCaseTest extends UseCaseTest {
    protected void assertCart(PresentableCart cart, int itemNumber, double totalPrice) {
        assertEquals(itemNumber, cart.getItems().size());
        assertEquals(totalPrice, cart.getTotalPrice(), PRICE_PRECISION);
    }

    protected void assertNotInCart(PresentableCart cart, String productId) {
        for (PresentableItem item : cart.getItems())
            if (item.getProductId().equals(productId))
                fail();
    }

    protected void assertInCart(PresentableCart cart, String productId, String name, String description, String pictureURI, int numberOfUnits, double price) {
        for (PresentableItem item : cart.getItems())
            if (item.getProductId().equals(productId)) {
                assertPresentedItem(item, name, description, pictureURI, numberOfUnits, price);
                return;
            }
        fail();
    }

    private void assertPresentedItem(PresentableItem item, String name, String description, String pictureURI, int numberOfUnits, double price) {
        assertEquals(name, item.getName());
        assertEquals(description, item.getDescription());
        assertEquals(pictureURI, item.getPictureURI());
        assertEquals(numberOfUnits, item.getNumberOfUnits());
        assertEquals(price, item.getPrice(), PRICE_PRECISION);
        assertEquals(price * numberOfUnits, item.getTotalPrice(), PRICE_PRECISION);
    }

    protected void assertProductUnits(String id, int numberOfUnits) {
        PresentableProduct product = presentProduct(id);
        assertEquals(numberOfUnits, product.getNumberOfUnits());
    }
}
