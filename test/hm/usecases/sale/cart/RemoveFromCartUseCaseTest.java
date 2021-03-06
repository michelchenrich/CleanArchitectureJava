package hm.usecases.sale.cart;

import hm.boundaries.delivery.sale.cart.PresentableCart;
import hm.usecases.UseCaseTest;
import org.junit.Before;
import org.junit.Test;

public class RemoveFromCartUseCaseTest extends UseCaseTest {
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
        removeFromCart(customerId, productId);
        PresentableCart cart = presentCart(customerId);
        assertSaleAttributes(cart, 0, 0.0);
        assertNotInCart(cart, productId);
        assertProductUnits(productId, 10);
    }

    @Test
    public void shouldRemoveOnlyTheDroppedProduct() {
        String productId2 = createDefaultProduct();
        addUnitToProduct(productId2, 10);
        addProductToCart(customerId, productId2, 10);

        removeFromCart(customerId, productId);
        PresentableCart cart = presentCart(customerId);
        assertSaleAttributes(cart, 1, 100.0);
        assertNotInCart(cart, productId);
        assertSaleItem(cart, productId2, "Name", "Description", "PictureURI", 10, 10.0);
    }

    @Test
    public void dropFromNonexistentCustomersCart() {
        removeFromCart("nonexistent", productId);
        assertNotFound("nonexistent");
    }

    @Test
    public void dropNonexistentProductFromCart() {
        removeFromCart(customerId, "nonexistent");
        assertNotFound("nonexistent");
        PresentableCart cart = presentCart(customerId);
        assertSaleAttributes(cart, 1, 100.0);
        assertSaleItem(cart, productId, "Name", "Description", "PictureURI", 10, 10.0);
    }

    @Test
    public void droppingAProductThatIsNotInCartShouldHaveNoEffect() {
        String productId2 = createDefaultProduct();

        removeFromCart(customerId, productId2);
        PresentableCart cart = presentCart(customerId);
        assertSaleAttributes(cart, 1, 100.0);
        assertSaleItem(cart, productId, "Name", "Description", "PictureURI", 10, 10.0);
        assertProductUnits(productId2, 0);
    }

    @Test
    public void dropAllAndRestoreNumberOfUnits() {
        String productId2 = createDefaultProduct();
        addUnitToProduct(productId2, 50);
        addProductToCart(customerId, productId2, 50);

        String productId3 = createDefaultProduct();
        addUnitToProduct(productId3, 15);
        addProductToCart(customerId, productId3, 15);

        dropCart(customerId);
        PresentableCart cart = presentCart(customerId);
        assertSaleAttributes(cart, 0, 0.0);
        assertNotInCart(cart, productId);
        assertNotInCart(cart, productId2);
        assertNotInCart(cart, productId3);

        assertProductUnits(productId, 10);
        assertProductUnits(productId2, 50);
        assertProductUnits(productId3, 15);
    }

    @Test
    public void dropAllFromNonexistentCustomer() {
        dropCart("nonexistent");
        assertNotFound("nonexistent");
    }
}