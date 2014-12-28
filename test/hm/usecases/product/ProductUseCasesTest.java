package hm.usecases.product;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import hm.usecases.UseCaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(HierarchicalContextRunner.class)
public class ProductUseCasesTest extends UseCaseTest {
    @Test
    public void whenJustCreatedShouldHaveNoUnits() {
        String id = createDefaultProduct();

        assertProduct(id, "Name", "Description", "PictureURI", 10.0, 0);
    }

    @Test
    public void afterAddingUnitsShouldHaveThemPresented() {
        String id = createProduct("Name 2", "Description 2", "PictureURI 2", 10.0);
        addUnitToProduct(id, 2);
        addUnitToProduct(id, 3);
        assertProduct(id, "Name 2", "Description 2", "PictureURI 2", 10.0, 5);
    }

    @Test
    public void afterUpdating1() {
        String id = createDefaultProduct();
        addUnitToProduct(id, 2);
        addUnitToProduct(id, 3);
        updateProduct(id, "New Name", "New Description", "New PictureURI", 5.0);
        assertProduct(id, "New Name", "New Description", "New PictureURI", 5.0, 5);
    }

    @Test
    public void afterUpdating2() {
        String id = createProduct("Name", "Description", "PictureURI", 9.0);
        addUnitToProduct(id, 3);
        updateProduct(id, "New Name 2", "New Description 2", "New PictureURI 2", 10.0);
        assertProduct(id, "New Name 2", "New Description 2", "New PictureURI 2", 10.0, 3);
    }

    public class IdentityValidations {
        @Test
        public void presentingNonexistentProduct() {
            presentProduct("nonexistent");
            assertNotFound("nonexistent");
        }

        @Test
        public void updatingNonexistentProduct() {
            updateProduct("nonexistent", "Name", "Description", "PictureURI", 10.0);
            assertNotFound("nonexistent");
        }

        @Test
        public void addUnitToNonexistentProduct() {
            addUnitToProduct("nonexistent", 10);
            assertNotFound("nonexistent");
        }
    }
    private abstract class DataValidationOnPersistence {
        @Test
        public void invalidInputs() throws Exception {
            persistProduct("", "Description", "PictureURI", 10.0);
            assertErrorsSent("nameIsEmpty");
            assertNothingChanged();

            persistProduct(" \r\n  ", "Description", "PictureURI", 10.0);
            assertErrorsSent("nameIsEmpty");
            assertNothingChanged();

            persistProduct(null, "Description", "PictureURI", 10.0);
            assertErrorsSent("nameIsEmpty");
            assertNothingChanged();

            persistProduct("Name", "", "PictureURI", 10.0);
            assertErrorsSent("descriptionIsEmpty");
            assertNothingChanged();

            persistProduct("Name", " \r\n  ", "PictureURI", 10.0);
            assertErrorsSent("descriptionIsEmpty");
            assertNothingChanged();

            persistProduct("Name", null, "PictureURI", 10.0);
            assertErrorsSent("descriptionIsEmpty");
            assertNothingChanged();

            persistProduct("Name", "Description", "", 10.0);
            assertErrorsSent("pictureURIIsEmpty");
            assertNothingChanged();

            persistProduct("Name", "Description", " \r\n  ", 10.0);
            assertErrorsSent("pictureURIIsEmpty");
            assertNothingChanged();

            persistProduct("Name", "Description", null, 10.0);
            assertErrorsSent("pictureURIIsEmpty");
            assertNothingChanged();

            persistProduct("Name", "Description", "PictureURI", -0.01);
            assertErrorsSent("priceIsNegative");
            assertNothingChanged();

            persistProduct(null, null, null, -0.01);
            assertErrorsSent("nameIsEmpty", "descriptionIsEmpty", "pictureURIIsEmpty", "priceIsNegative");
        }

        protected abstract void persistProduct(String name, String description, String pictureURI, double price);
        protected abstract void assertNothingChanged();
    }
    public class DataValidationOnCreation extends DataValidationOnPersistence {
        protected void persistProduct(String name, String description, String pictureURI, double price) {
            createProduct(name, description, pictureURI, price);
        }

        protected void assertNothingChanged() {
            assertNothingCreated();
        }
    }
    public class DataValidationOnUpdate extends DataValidationOnPersistence {
        private String id;

        @Before
        public void setUp() {
            id = createProduct("Original Name", "Original Description", "Original PictureURI", 1.1);
        }

        protected void persistProduct(String name, String description, String pictureURI, double price) {
            updateProduct(id, name, description, pictureURI, price);
        }

        protected void assertNothingChanged() {
            assertProduct(id, "Original Name", "Original Description", "Original PictureURI", 1.1, 0);
        }
    }
}