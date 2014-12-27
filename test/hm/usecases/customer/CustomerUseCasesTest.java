package hm.usecases.customer;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import hm.UseCaseTest;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(HierarchicalContextRunner.class)
public class CustomerUseCasesTest extends UseCaseTest {
    public class ReadTests {
        @Test
        public void readNonexistentCustomer() {
            presentCustomer("nonexistent-id");

            assertNotFound();
        }

        @Test
        public void readPresentableCustomer() {
            String id1 = createCustomer("First", "Last");
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

            executeUseCase(" \r\n  ", "Last");
            assertErrorsSent("firstNameIsEmpty");

            executeUseCase(null, "Last");
            assertErrorsSent("firstNameIsEmpty");

            executeUseCase("First", "");
            assertErrorsSent("lastNameIsEmpty");

            executeUseCase("First", " \r\n  ");
            assertErrorsSent("lastNameIsEmpty");

            executeUseCase("First", null);
            assertErrorsSent("lastNameIsEmpty");

            executeUseCase(null, null);
            assertErrorsSent("firstNameIsEmpty", "lastNameIsEmpty");
        }

        protected abstract void executeUseCase(String firstName, String lastName);

        protected abstract void assertNothingChanged();

    }

    public class DataValidationsOnCreation extends DataValidationsOnPersistence {
        @Test
        public void createCustomerThenReadItBack() {
            String id = createCustomer("First", "Last");
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
            id = createCustomer("First", "Last");
        }

        @Test
        public void cannotUpdateNonexistentCustomer() throws Exception {
            updateCustomer("nonexistent-id", "First", "Last");
            assertNotFound();
            assertErrorsNotSent("firstNameIsEmpty", "lastNameIsEmpty");

            updateCustomer("nonexistent-id", null, "");
            assertNotFound();
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
        Customer customer = presentCustomer(id);
        assertFound();
        assertEquals(name, customer.getName());
    }
}