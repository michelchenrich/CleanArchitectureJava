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
    public void dropProductAndRestoreNumberOfUnits() {
        dropProductFromCart(customerId, productId);
        assertNotInCart(customerId, productId);
        assertProduct(productId, "Name", "Description", "PictureURI", 10.0, 10);
    }

    @Test
    public void shouldRemoveOnlyTheDroppedProduct() {
        String productId2 = createDefaultProduct();
        addUnitToProduct(productId2, 10);
        addProductToCart(customerId, productId2, 10);

        dropProductFromCart(customerId, productId);
        assertNotInCart(customerId, productId);
        assertInCart(customerId, productId2, 10, 10.0);
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

    @Test
    public void droppingAProductThatIsNotInCartShouldHaveNoEffect() {
        String productId2 = createDefaultProduct();

        dropProductFromCart(customerId, productId2);
        assertInCart(customerId, productId, 10, 10.0);
        assertProduct(productId2, "Name", "Description", "PictureURI", 10.0, 0);
    }

    @Test
    public void dropAllAndRestoreNumberOfUnits() {
        String productId2 = createDefaultProduct();
        addUnitToProduct(productId2, 50);
        addProductToCart(customerId, productId2, 50);

        String productId3 = createDefaultProduct();
        addUnitToProduct(productId3, 15);
        addProductToCart(customerId, productId3, 15);

        dropAllFromCart(customerId);
        assertNotInCart(customerId, productId);
        assertNotInCart(customerId, productId2);
        assertNotInCart(customerId, productId3);
        assertProduct(productId, "Name", "Description", "PictureURI", 10.0, 10);
        assertProduct(productId2, "Name", "Description", "PictureURI", 10.0, 50);
        assertProduct(productId3, "Name", "Description", "PictureURI", 10.0, 15);
    }
}