package hm.usecases.sale;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class SubmitSaleOrderUseCaseTest extends CartUseCaseTest {
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
    public void itsSomething() {
        SaleOrder order = submitOrder(customerId);
        assertEquals(750.0, order.getTotalPrice(), .001);
    }
}
