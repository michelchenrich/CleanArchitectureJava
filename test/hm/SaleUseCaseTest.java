package hm;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import hm.usecases.sale.CustomerCart;
import hm.usecases.sale.CustomerCartItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(HierarchicalContextRunner.class)
public class SaleUseCaseTest extends UseCaseTest {
    @Test
    public void itsSomething() {
        String customerId = createCustomer("First", "Last");
        String productId = createProduct("Name", "Description", "PictureURI", 10.0);
        addUnitToProduct(productId, 1);
        addProductToCart(customerId, productId, 1);
        assertInCart(customerId, productId, 1, 10.0);
        assertProduct(productId, "Name", "Description", "PictureURI", 10.0, 0);
    }

    private void assertInCart(String customerId, String productId, int numberOfUnits, double price) {
        CustomerCart cart = presentCustomerCart(customerId);
        for (CustomerCartItem item : cart.getItems())
            if (item.getProductId().equals(productId) && item.getPrice() == price) {
                assertEquals(numberOfUnits, item.getNumberOfUnits());
                return;
            }
        fail();
    }
}
