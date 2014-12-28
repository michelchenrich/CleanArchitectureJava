package hm.usecases.saleorder;

import hm.entities.SaleOrder;
import hm.usecases.cart.CartUseCaseTest;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
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

    @Ignore
    @Test
    public void itsSomething() { //TODO Continue here
        String orderId = submitOrder(customerId);
        SaleOrder order = presentOrder(orderId);
        assertEquals(750.0, order.getTotalPrice(), .001);
    }
}
