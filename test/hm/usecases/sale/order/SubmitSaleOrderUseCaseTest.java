package hm.usecases.sale.order;

import hm.usecases.UseCaseTest;
import hm.usecases.sale.PresentableSale;
import org.junit.Before;
import org.junit.Test;

public class SubmitSaleOrderUseCaseTest extends UseCaseTest {
    private String customerId;
    private String productId;
    private String productId2;
    private String productId3;

    @Before
    public void setUpForSale() {
        customerId = createDefaultCustomer();

        productId = createDefaultProduct();
        addUnitToProduct(productId, 10);
        addProductToCart(customerId, productId, 10);

        productId2 = createDefaultProduct();
        addUnitToProduct(productId2, 50);
        addProductToCart(customerId, productId2, 50);

        productId3 = createDefaultProduct();
        addUnitToProduct(productId3, 15);
        addProductToCart(customerId, productId3, 15);
    }

    @Test
    public void submittingOrderShouldPersistTheCartAsASaleOrderThenClearTheCart() {
        String orderId = submitOrder(customerId);

        PresentableSale cart = presentCart(customerId);
        assertAttributes(cart, 0, 0.0);

        PresentableSale order = presentOrder(orderId);
        assertAttributes(order, 3, 750.0);
        assertHasItem(order, productId, "Name", "Description", "PictureURI", 10, 10.0);
        assertHasItem(order, productId2, "Name", "Description", "PictureURI", 50, 10.0);
        assertHasItem(order, productId3, "Name", "Description", "PictureURI", 15, 10.0);
    }

    @Test
    public void updatingProductPricesMustNotChangeTheSaleOrder() {
        String orderId = submitOrder(customerId);

        updateProduct(productId, "Name", "Description", "PictureURI", 11.0);
        updateProduct(productId2, "Name", "Description", "PictureURI", 11.0);
        updateProduct(productId3, "Name", "Description", "PictureURI", 11.0);

        PresentableSale order = presentOrder(orderId);
        assertAttributes(order, 3, 750.0);
        assertHasItem(order, productId, "Name", "Description", "PictureURI", 10, 10.0);
        assertHasItem(order, productId2, "Name", "Description", "PictureURI", 50, 10.0);
        assertHasItem(order, productId3, "Name", "Description", "PictureURI", 15, 10.0);
    }

    @Test
    public void submittingOrderFromNonexistentCustomer() {
        submitOrder("nonexistent");
        assertNotFound("nonexistent");
    }

    @Test
    public void presentingNonexistentSaleOrder() {
        presentOrder("nonexistent");
        assertNotFound("nonexistent");
    }
}