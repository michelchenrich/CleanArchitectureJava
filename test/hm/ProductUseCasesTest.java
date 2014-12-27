package hm;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import hm.usecases.product.Product;
import hm.usecases.product.ProductUnit;
import hm.usecases.product.ProductUseCaseFactory;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(HierarchicalContextRunner.class)
public class ProductUseCasesTest {
    public static final double PRICE_PRECISION = .001;
    private FakeResponder responder;
    private FakeRequest request;
    private ProductUseCaseFactory factory;

    @Before
    public void setUp() {
        factory = new ProductUseCaseFactory(new FakeGateway<>());
    }

    @Test
    public void whenJustCreatedShouldHaveNoUnits() {
        String id = createProduct("Name", "Description", "PictureURI");
        Product product = presentProduct(id);
        assertPresented(product, "Name", "Description", "PictureURI", 0);
    }

    @Test
    public void afterAddingUnitSShouldHaveThemPresented() {
        String id = createProduct("Name 2", "Description 2", "PictureURI 2");
        addUnitToProduct(id, 2, 10.0);
        addUnitToProduct(id, 3, 9.0);
        Product product = presentProduct(id);
        assertPresented(product, "Name 2", "Description 2", "PictureURI 2", 5, 10.0, 10.0, 9.0, 9.0, 9.0);
    }

    @Test
    public void cannotAddUnitsWithNegativePrice() {
        String id = createProduct("Name", "Description", "PictureURI");
        addUnitToProduct(id, 10, -0.01);
        assertTrue(responder.priceIsNegative);
        Product product = presentProduct(id);
        assertPresented(product, "Name", "Description", "PictureURI", 0);
    }

    @Test
    public void afterUpdating1() {
        String id = createProduct("Name", "Description", "PictureURI");
        addUnitToProduct(id, 2, 10.0);
        addUnitToProduct(id, 3, 9.0);
        updateProduct(id, "New Name", "New Description", "New PictureURI");
        Product product = presentProduct(id);
        assertPresented(product, "New Name", "New Description", "New PictureURI", 5, 10.0, 10.0, 9.0, 9.0, 9.0);
    }

    @Test
    public void afterUpdating2() {
        String id = createProduct("Name", "Description", "PictureURI");
        addUnitToProduct(id, 3, 9.0);
        updateProduct(id, "New Name 2", "New Description 2", "New PictureURI 2");
        Product product = presentProduct(id);
        assertPresented(product, "New Name 2", "New Description 2", "New PictureURI 2", 3, 9.0, 9.0, 9.0);
    }

    public class IdentityValidations {
        @Test
        public void presentingNonexistentProduct() {
            presentProduct("nonexistent");
            assertNotFound();
        }

        @Test
        public void updatingNonexistentProduct() {
            updateProduct("nonexistent", "Name", "Description", "PictureURI");
            assertNotFound();
        }

        @Test
        public void addUnitToNonexistentProduct() {
            addUnitToProduct("nonexistent", 10, 10.0);
            assertNotFound();
        }
    }

    private abstract class DataValidationOnPersistence {
        @Test
        public void invalidInputs() {
            persistProduct("", "Description", "PictureURI");
            assertMessagesSent(responder.nameIsEmpty);

            persistProduct(" \r\n  ", "Description", "PictureURI");
            assertMessagesSent(responder.nameIsEmpty);

            persistProduct(null, "Description", "PictureURI");
            assertMessagesSent(responder.nameIsEmpty);

            persistProduct("Name", "", "PictureURI");
            assertMessagesSent(responder.descriptionIsEmpty);

            persistProduct("Name", " \r\n  ", "PictureURI");
            assertMessagesSent(responder.descriptionIsEmpty);

            persistProduct("Name", null, "PictureURI");
            assertMessagesSent(responder.descriptionIsEmpty);

            persistProduct("Name", "Description", "");
            assertMessagesSent(responder.pictureURIIsEmpty);

            persistProduct("Name", "Description", " \r\n  ");
            assertMessagesSent(responder.pictureURIIsEmpty);

            persistProduct("Name", "Description", null);
            assertMessagesSent(responder.pictureURIIsEmpty);

            persistProduct(null, null, null);
            assertMessagesSent(responder.nameIsEmpty && responder.descriptionIsEmpty && responder.pictureURIIsEmpty);
        }

        protected abstract void persistProduct(String name, String description, String pictureURI);

        private void assertMessagesSent(boolean messageWasSent) {
            assertTrue(messageWasSent);
            assertNothingChanged();
        }

        protected abstract void assertNothingChanged();

    }

    public class DataValidationOnCreation extends DataValidationOnPersistence {

        protected void persistProduct(String name, String description, String pictureURI) {
            createProduct(name, description, pictureURI);
        }

        protected void assertNothingChanged() {
            assertNull(responder.createdWithId);
        }

    }

    public class DataValidationOnUpdate extends DataValidationOnPersistence {

        private String id;

        @Before
        public void setUp() {
            id = createProduct("Original Name", "Original Description", "Original PictureURI");
        }

        protected void persistProduct(String name, String description, String pictureURI) {
            updateProduct(id, name, description, pictureURI);
        }

        protected void assertNothingChanged() {
            Product product = presentProduct(id);
            assertPresented(product, "Original Name", "Original Description", "Original PictureURI", 0);
        }

    }

    private void addUnitToProduct(String id, int amount, double price) {
        request = new FakeRequest();
        request.id = id;
        request.numberOfUnits = amount;
        request.price = price;
        responder = new FakeResponder();
        factory.makeUnitAdder(request, responder).execute();
    }

    private void updateProduct(String id, String name, String description, String pictureURI) {
        request = new FakeRequest();
        request.id = id;
        request.name = name;
        request.description = description;
        request.pictureURI = pictureURI;
        responder = new FakeResponder();
        factory.makeUpdater(request, responder).execute();
    }

    private String createProduct(String name, String description, String pictureURI) {
        request = new FakeRequest();
        request.name = name;
        request.description = description;
        request.pictureURI = pictureURI;
        responder = new FakeResponder();
        factory.makeCreator(request, responder).execute();
        return responder.createdWithId;
    }

    private Product presentProduct(String id) {
        request = new FakeRequest();
        request.id = id;
        responder = new FakeResponder();
        factory.makePresenter(request, responder).execute();
        return responder.product;
    }

    private void assertPresented(Product product, String name, String description, String pictureURI, int numberOfUnits, double... prices) {
        assertFalse(responder.invalidId);

        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(pictureURI, product.getPictureURI());

        List<ProductUnit> units = product.getUnits();
        assertEquals(numberOfUnits, units.size());
        for (int i = 0; i < numberOfUnits; i++) {
            assertEquals(product.getId(), units.get(i).getProductId());
            assertEquals(prices[i], units.get(i).getPrice(), PRICE_PRECISION);
        }
    }

    private void assertNotFound() {
        assertTrue(responder.invalidId);
    }
}