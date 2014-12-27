package hm;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import hm.usecases.customer.Customer;
import hm.usecases.customer.CustomerUseCaseFactory;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(HierarchicalContextRunner.class)
public class CustomerUseCasesTest {
    private FakeResponder responder;
    private FakeRequest request;
    private FakeGateway<Customer> gateway;
    private CustomerUseCaseFactory factory;

    @Before
    public void setUp() {
        gateway = new FakeGateway<>();
        factory = new CustomerUseCaseFactory(gateway);
    }

    public class ReadTests {
        @Test
        public void readNonexistentCustomer() {
            presentCustomer("nonexistent-id");

            assertTrue(responder.invalidId);
        }

        @Test
        public void readPresentableCustomer() {
            String id1 = createCustomer("First", "Last");
            String id2 = createCustomer("First 1", "Last 2");

            assertPresentableCustomer(id2, "Last 2, First 1");
            assertPresentableCustomer(id1, "Last, First");
        }

        private void assertPresentableCustomer(String id, String name) {
            presentCustomer(id);
            assertFalse(responder.invalidId);
            assertEquals(name, responder.customer.getName());
        }

        private void presentCustomer(String id) {
            request = new FakeRequest();
            request.id = id;
            responder = new FakeResponder();
            factory.makePresenter(request, responder).execute();
        }
    }

    private abstract class PersistenceTests {
        @Test
        public void validations() {
            executeUseCase("", "Last");
            assertValidationError(responder.firstNameIsEmpty);

            executeUseCase(" \r\n  ", "Last");
            assertValidationError(responder.firstNameIsEmpty);

            executeUseCase(null, "Last");
            assertValidationError(responder.firstNameIsEmpty);

            executeUseCase("First", "");
            assertValidationError(responder.lastNameIsEmpty);

            executeUseCase("First", " \r\n  ");
            assertValidationError(responder.lastNameIsEmpty);

            executeUseCase("First", null);
            assertValidationError(responder.lastNameIsEmpty);

            executeUseCase(null, null);
            assertValidationError(responder.firstNameIsEmpty && responder.lastNameIsEmpty);
        }

        protected abstract void executeUseCase(String firstName, String lastName);

        private void assertValidationError(boolean messageWasSent) {
            assertTrue(messageWasSent);
            assertNothingChanged();
        }

        protected abstract void assertNothingChanged();

        protected void assertPersisted(String id, String firstName, String lastName) {
            Customer customer = gateway.findById(id);
            assertEquals(firstName, customer.getFirstName());
            assertEquals(lastName, customer.getLastName());
        }
    }

    public class CreateTests extends PersistenceTests {
        @Test
        public void createCustomerThenReadItBack() {
            String id = createCustomer("First", "Last");
            assertPersisted(id, "First", "Last");
        }

        protected void executeUseCase(String firstName, String lastName) {
            createCustomer(firstName, lastName);
        }

        protected void assertNothingChanged() {
            assertNull(responder.createdWithId);
        }
    }

    public class UpdateTests extends PersistenceTests {
        private String id;

        @Before
        public void setUp() {
            id = createCustomer("First", "Last");
        }

        @Test
        public void cannotUpdateNonexistentCustomer() {
            updateCustomer("nonexistent-id", "First", "Last");
            assertTrue(responder.invalidId);
            assertFalse(responder.firstNameIsEmpty);
            assertFalse(responder.lastNameIsEmpty);

            updateCustomer("nonexistent-id", null, "");
            assertTrue(responder.invalidId);
            assertTrue(responder.firstNameIsEmpty);
            assertTrue(responder.lastNameIsEmpty);
        }

        @Test
        public void updateCustomerThenReadItBack1() {
            updateCustomer(id, "First 2", "Last 2");
            assertPersisted(id, "First 2", "Last 2");
        }

        @Test
        public void updateCustomerThenReadItBack2() {
            updateCustomer(id, "First 3", "Last 3");
            assertPersisted(id, "First 3", "Last 3");
        }

        protected void executeUseCase(String firstName, String lastName) {
            updateCustomer(id, firstName, lastName);
        }

        protected void assertNothingChanged() {
            assertPersisted(id, "First", "Last");
        }

        private void updateCustomer(String id, String firstName, String lastName) {
            request = new FakeRequest();
            request.id = id;
            request.firstName = firstName;
            request.lastName = lastName;
            responder = new FakeResponder();
            factory.makeUpdater(request, responder).execute();
        }
    }

    protected String createCustomer(String firstName, String lastName) {
        request = new FakeRequest();
        request.firstName = firstName;
        request.lastName = lastName;
        responder = new FakeResponder();
        factory.makeCreator(request, responder).execute();
        return responder.createdWithId;
    }
}