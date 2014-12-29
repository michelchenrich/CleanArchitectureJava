package hm.usecases.sale.order;

import hm.usecases.UseCaseTest;
import hm.usecases.sale.PresentableSale;
import org.junit.Before;
import org.junit.Test;

public class CancelSaleOrderUseCaseTest extends UseCaseTest {
    private String productId;
    private String productId2;
    private String productId3;
    private String orderId;

    @Before
    public void setUpForSale() {
        String customerId = createDefaultCustomer();

        productId = createDefaultProduct();
        addUnitToProduct(productId, 10);
        addProductToCart(customerId, productId, 10);

        productId2 = createDefaultProduct();
        addUnitToProduct(productId2, 50);
        addProductToCart(customerId, productId2, 50);

        productId3 = createDefaultProduct();
        addUnitToProduct(productId3, 15);
        addProductToCart(customerId, productId3, 15);

        orderId = submitOrder(customerId);
    }

    @Test
    public void afterCanceling_theOrderMustExistAsCanceled_andTheProductUnitsShouldBeRestored() {
        cancelOrder(orderId);

        PresentableSale order = presentOrder(orderId);
        assertSaleAttributes(order, 3, 750.0);
        assertSaleItem(order, productId, "Name", "Description", "PictureURI", 10, 10.0);
        assertSaleItem(order, productId2, "Name", "Description", "PictureURI", 50, 10.0);
        assertSaleItem(order, productId3, "Name", "Description", "PictureURI", 15, 10.0);
        assertSaleOrderIsCanceled(order, true);

        assertProductUnits(productId, 10);
        assertProductUnits(productId2, 50);
        assertProductUnits(productId3, 15);
    }

    @Test
    public void cancellingNonexistentOrder() {
        cancelOrder("nonexistent");
        assertNotFound("nonexistent");
    }
}
