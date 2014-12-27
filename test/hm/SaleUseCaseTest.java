package hm;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import hm.usecases.sale.Cart;
import hm.usecases.sale.CartItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(HierarchicalContextRunner.class)
public class SaleUseCaseTest extends UseCaseTest {

    private String customerId;
    private String productId;

    @Before
    public void setUpForSale() {
        customerId = createCustomer("First", "Last");
        productId = createProduct("Name", "Description", "PictureURI", 10.0);
        addUnitToProduct(productId, 1);
    }

    @Test
    public void addToCart() {
        addProductToCart(customerId, productId, 1);

        assertInCart(customerId, productId, 1, 10.0);
        assertProduct(productId, "Name", "Description", "PictureURI", 10.0, 0);
    }

    @Test
    public void afterAddingToCartPriceUpdatesShouldNotBeReflected() {
        addProductToCart(customerId, productId, 1);

        updateProduct(productId, "Name", "Description", "PictureURI", 11.0);

        assertInCart(customerId, productId, 1, 10.0);
        assertProduct(productId, "Name", "Description", "PictureURI", 11.0, 0);
    }

    @Test
    public void presentingCartOfNonexistentCustomer() {
        presentCustomerCart("nonexistent");
        assertNotFound("nonexistent");
    }

    @Test
    public void addToCartOfNonexistentCustomer() {
        addProductToCart("nonexistent", productId, 1);
        assertNotFound("nonexistent");
    }

    @Test
    public void addToNonexistentProductCart() {
        addProductToCart(customerId, "nonexistent", 1);
        assertNotFound("nonexistent");
    }

    private void assertInCart(String customerId, String productId, int numberOfUnits, double price) {
        Cart cart = presentCustomerCart(customerId);
        for (CartItem item : cart.getItems())
            if (item.getProductId().equals(productId) && item.getPrice() == price) {
                assertEquals(numberOfUnits, item.getNumberOfUnits());
                return;
            }
        fail();
    }
}
