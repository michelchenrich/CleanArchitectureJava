package hm.usecases.customer;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import hm.boundaries.delivery.customer.PresentableCustomer;
import hm.usecases.UseCaseTest;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(HierarchicalContextRunner.class)
public class CustomerUseCasesTest extends UseCaseTest {
    public class ReadTests {
        @Test
        public void readNonexistentCustomer() {
            presentCustomer("nonexistent");

            assertNotFound("nonexistent");
        }

        @Test
        public void readPresentableCustomer() {
            String id1 = createDefaultCustomer();
            String id2 = createCustomer("First 1", "Last 2");

            assertPresented(id2, "Last 2, First 1");
            assertPresented(id1, "Last, First");
        }
    }
    private abstract class DataValidationsOnPersistence {
        @Test
        public void validations() throws Exception {
            executeUseCase("", "Last");
            assertErrorsSent("firstNameIsEmpty");
            assertNothingChanged();

            executeUseCase(" \r\n  ", "Last");
            assertErrorsSent("firstNameIsEmpty");
            assertNothingChanged();

            executeUseCase(null, "Last");
            assertErrorsSent("firstNameIsEmpty");
            assertNothingChanged();

            executeUseCase("First", "");
            assertErrorsSent("lastNameIsEmpty");
            assertNothingChanged();

            executeUseCase("First", " \r\n  ");
            assertErrorsSent("lastNameIsEmpty");
            assertNothingChanged();

            executeUseCase("First", null);
            assertErrorsSent("lastNameIsEmpty");
            assertNothingChanged();

            executeUseCase(null, null);
            assertErrorsSent("firstNameIsEmpty", "lastNameIsEmpty");
            assertNothingChanged();
        }

        protected abstract void executeUseCase(String firstName, String lastName);
        protected abstract void assertNothingChanged();
    }
    public class DataValidationsOnCreation extends DataValidationsOnPersistence {
        @Test
        public void createCustomerThenReadItBack() {
            String id = createDefaultCustomer();
            assertPresented(id, "Last" + ", " + "First");
        }

        protected void executeUseCase(String firstName, String lastName) {
            createCustomer(firstName, lastName);
        }

        protected void assertNothingChanged() {
            assertNothingCreated();
        }
    }
    public class DataValidationsOnUpdate extends DataValidationsOnPersistence {
        private String id;

        @Before
        public void setUp() {
            id = createDefaultCustomer();
        }

        @Test
        public void cannotUpdateNonexistentCustomer() throws Exception {
            updateCustomer("nonexistent", "First", "Last");
            assertNotFound("nonexistent");
            assertErrorsNotSent("firstNameIsEmpty", "lastNameIsEmpty");

            updateCustomer("nonexistent", null, "");
            assertNotFound("nonexistent");
            assertErrorsSent("firstNameIsEmpty", "lastNameIsEmpty");
        }

        @Test
        public void updateCustomerThenReadItBack1() {
            updateCustomer(id, "First 2", "Last 2");
            assertPresented(id, "Last 2" + ", " + "First 2");
        }

        @Test
        public void updateCustomerThenReadItBack2() {
            updateCustomer(id, "First 3", "Last 3");
            assertPresented(id, "Last 3" + ", " + "First 3");
        }

        protected void executeUseCase(String firstName, String lastName) {
            updateCustomer(id, firstName, lastName);
        }

        protected void assertNothingChanged() {
            assertPresented(id, "Last" + ", " + "First");
        }
    }

    private void assertPresented(String id, String name) {
        PresentableCustomer customer = presentCustomer(id);
        assertFound();
        assertEquals(name, customer.getName());
    }
}