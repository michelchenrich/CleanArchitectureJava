package hm;

import hm.usecases.customer.Customer;
import hm.usecases.customer.CustomerUseCaseFactory;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CustomerUseCasesTest {
    private FakeResponder responder;
    private FakeRequest request;
    private FakeGateway gateway;
    private CustomerUseCaseFactory factory;

    @Before
    public void setUp() {
        gateway = new FakeGateway();
        factory = new CustomerUseCaseFactory(new FakeContext(gateway));
    }

    @Test
    public void readNonexistentCustomer() {
        presentCustomer("nonexistent-id");

        assertTrue(responder.customerNotFound);
    }

    @Test
    public void readPresentableCustomer() {
        String id1 = createCustomer("First", "Last");
        String id2 = createCustomer("First 1", "Last 2");

        assertPresentableCustomer(id2, "Last 2, First 1");
        assertPresentableCustomer(id1, "Last, First");
    }

    @Test
    public void cannotCreateCustomerWithInvalidInput() {
        createCustomer("", "Last");
        assertTrue(responder.firstNameIsEmpty);
        assertNull(responder.createdWithId);

        createCustomer(" \r\n  ", "Last");
        assertTrue(responder.firstNameIsEmpty);
        assertNull(responder.createdWithId);

        createCustomer(null, "Last");
        assertTrue(responder.firstNameIsEmpty);
        assertNull(responder.createdWithId);

        createCustomer("First", "");
        assertTrue(responder.lastNameIsEmpty);
        assertNull(responder.createdWithId);

        createCustomer("First", " \r\n  ");
        assertTrue(responder.lastNameIsEmpty);
        assertNull(responder.createdWithId);

        createCustomer("First", null);
        assertTrue(responder.lastNameIsEmpty);
        assertNull(responder.createdWithId);

        createCustomer(null, null);
        assertTrue(responder.firstNameIsEmpty);
        assertTrue(responder.lastNameIsEmpty);
        assertNull(responder.createdWithId);
    }

    @Test
    public void createCustomerThenReadItBack() {
        String id = createCustomer("First", "Last");
        assertPersisted(id, "First", "Last");
    }

    @Test
    public void cannotUpdateCustomerWithInvalidInput() {
        String id = createCustomer("First", "Last");

        updateCustomer(id, "", "Last");
        assertTrue(responder.firstNameIsEmpty);
        assertPersisted(id, "First", "Last");

        updateCustomer(id, " \r\n  ", "Last");
        assertTrue(responder.firstNameIsEmpty);
        assertPersisted(id, "First", "Last");

        updateCustomer(id, null, "Last");
        assertTrue(responder.firstNameIsEmpty);
        assertPersisted(id, "First", "Last");

        updateCustomer(id, "First", "");
        assertTrue(responder.lastNameIsEmpty);
        assertPersisted(id, "First", "Last");

        updateCustomer(id, "First", " \r\n  ");
        assertTrue(responder.lastNameIsEmpty);
        assertPersisted(id, "First", "Last");

        updateCustomer(id, "First", null);
        assertTrue(responder.lastNameIsEmpty);
        assertPersisted(id, "First", "Last");

        updateCustomer(id, null, null);
        assertTrue(responder.firstNameIsEmpty);
        assertTrue(responder.lastNameIsEmpty);
        assertPersisted(id, "First", "Last");
    }

    @Test
    public void cannotUpdateNonexistentCustomer() {
        updateCustomer("nonexistent-id", "First", "Last");
        assertTrue(responder.customerNotFound);
        assertFalse(responder.firstNameIsEmpty);
        assertFalse(responder.lastNameIsEmpty);

        updateCustomer("nonexistent-id", null, "");
        assertTrue(responder.customerNotFound);
        assertTrue(responder.firstNameIsEmpty);
        assertTrue(responder.lastNameIsEmpty);
    }

    @Test
    public void updateCustomerThenReadItBack() {
        String id = createCustomer("First", "Last");
        updateCustomer(id, "First 2", "Last 2");
        assertPersisted(id, "First 2", "Last 2");
    }

    private void updateCustomer(String id, String firstName, String lastName) {
        request = new FakeRequest();
        request.id = id;
        request.firstName = firstName;
        request.lastName = lastName;
        responder = new FakeResponder();
        factory.makeUpdater(request, responder).execute();
    }

    private String createCustomer(String firstName, String lastName) {
        request = new FakeRequest();
        request.firstName = firstName;
        request.lastName = lastName;
        responder = new FakeResponder();
        factory.makeCreator(request, responder).execute();
        return responder.createdWithId;
    }

    private void presentCustomer(String id) {
        request = new FakeRequest();
        request.id = id;
        responder = new FakeResponder();
        factory.makePresenter(request, responder).execute();
    }

    private void assertPresentableCustomer(String id, String name) {
        presentCustomer(id);
        assertFalse(responder.customerNotFound);
        assertEquals(name, responder.presentableCustomer.getName());
    }

    private void assertPersisted(String id, String firstName, String lastName) {
        Customer customer = gateway.findById(id);
        assertEquals(firstName, customer.getFirstName());
        assertEquals(lastName, customer.getLastName());
    }
}