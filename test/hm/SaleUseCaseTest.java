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
    public void addNonexistentProductToCart() {
        addProductToCart(customerId, "nonexistent", 1);
        assertNotFound("nonexistent");
        assertNotInCart(customerId, productId);
    }

    @Test
    public void addNoUnitToCart() throws Exception {
        addProductToCart(customerId, productId, 0);
        assertErrorsSent("numberOfUnitsIsLessThanOne");
        assertNotInCart(customerId, productId);
    }


    @Test
    public void addOnlyAvailableUnitsToCart() throws Exception {
        addUnitToProduct(productId, 1);
        addUnitToProduct(productId, 1);
        addProductToCart(customerId, productId, 100);

        assertInCart(customerId, productId, 3, 10.0);
        assertProduct(productId, "Name", "Description", "PictureURI", 10.0, 0);
    }

    @Test
    public void addLessThanAllAvailableUnitsToCart() throws Exception {
        addUnitToProduct(productId, 100);
        addProductToCart(customerId, productId, 100);

        assertInCart(customerId, productId, 100, 10.0);
        assertProduct(productId, "Name", "Description", "PictureURI", 10.0, 1);
    }


    @Test
    public void addMultipleItemsToCart() throws Exception {
        String productId2 = createProduct("Name 2", "Description 2", "PictureURI 2", 12.0);
        addUnitToProduct(productId2, 10);

        String productId3 = createProduct("Name 3", "Description 3", "PictureURI 3", 15.0);
        addUnitToProduct(productId3, 9);

        addProductToCart(customerId, productId, 1);
        addProductToCart(customerId, productId2, 5);
        addProductToCart(customerId, productId3, 10);

        assertInCart(customerId, productId, 1, 10.0);
        assertInCart(customerId, productId2, 5, 12.0);
        assertInCart(customerId, productId3, 9, 15.0);
        assertProduct(productId, "Name", "Description", "PictureURI", 10.0, 0);
    }

    private void assertNotInCart(String customerId, String productId) {
        Cart cart = presentCustomerCart(customerId);
        for (CartItem item : cart.getItems())
            if (item.getProductId().equals(productId))
                fail();
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
