package hm.usecases.sale.cart;

import hm.usecases.UseCaseTest;
import hm.usecases.sale.PresentableSale;
import org.junit.Before;
import org.junit.Test;

public class AddToCartUseCaseTest extends UseCaseTest {
    private String customerId;
    private String productId;

    @Before
    public void setUpForSale() {
        customerId = createDefaultCustomer();
        productId = createDefaultProduct();
        addUnitToProduct(productId, 1);
    }

    @Test
    public void addToCart() {
        addProductToCart(customerId, productId, 1);

        assertProductUnits(productId, 0);

        PresentableSale cart = presentCart(customerId);
        assertAttributes(cart, 1, 10.0);
        assertHasItem(cart, productId, "Name", "Description", "PictureURI", 1, 10.0);
    }

    @Test
    public void afterAddingToCartPriceUpdatesShouldNotBeReflected() {
        addProductToCart(customerId, productId, 1);
        assertProductUnits(productId, 0);
        updateProduct(productId, "Name", "Description", "PictureURI", 11.0);

        PresentableSale cart = presentCart(customerId);
        assertAttributes(cart, 1, 10.0);
        assertHasItem(cart, productId, "Name", "Description", "PictureURI", 1, 10.0);
    }

    @Test
    public void presentingCartOfNonexistentCustomer() {
        presentCart("nonexistent");
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
        PresentableSale cart = presentCart(customerId);
        assertAttributes(cart, 0, 0.0);
        assertNotInCart(cart, productId);
    }

    @Test
    public void addNoUnitToCart() throws Exception {
        addProductToCart(customerId, productId, 0);
        assertErrorsSent("numberOfUnitsIsLessThanOne");
        PresentableSale cart = presentCart(customerId);
        assertAttributes(cart, 0, 0.0);
        assertNotInCart(cart, productId);
    }

    @Test
    public void addOnlyAvailableUnitsToCart() throws Exception {
        addUnitToProduct(productId, 1);
        addUnitToProduct(productId, 1);
        addProductToCart(customerId, productId, 100);
        assertProductUnits(productId, 0);

        PresentableSale cart = presentCart(customerId);
        assertAttributes(cart, 1, 30.0);
        assertHasItem(cart, productId, "Name", "Description", "PictureURI", 3, 10.0);
    }

    @Test
    public void addLessThanAllAvailableUnitsToCart() throws Exception {
        addUnitToProduct(productId, 100);
        addProductToCart(customerId, productId, 100);
        assertProductUnits(productId, 1);

        PresentableSale cart = presentCart(customerId);
        assertAttributes(cart, 1, 1000.0);
        assertHasItem(cart, productId, "Name", "Description", "PictureURI", 100, 10.0);
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

        PresentableSale cart = presentCart(customerId);
        assertAttributes(cart, 3, 205.0);
        assertHasItem(cart, productId, "Name", "Description", "PictureURI", 1, 10.0);
        assertHasItem(cart, productId2, "Name 2", "Description 2", "PictureURI 2", 5, 12.0);
        assertHasItem(cart, productId3, "Name 3", "Description 3", "PictureURI 3", 9, 15.0);
    }

    @Test
    public void addingSameProductToCartTwiceSimplySumsTheUnits() throws Exception {
        addUnitToProduct(productId, 10);

        addProductToCart(customerId, productId, 5);
        addProductToCart(customerId, productId, 5);

        PresentableSale cart = presentCart(customerId);
        assertAttributes(cart, 1, 100.0);
        assertHasItem(cart, productId, "Name", "Description", "PictureURI", 10, 10.0);
    }
}
