package hm.usecases;

import hm.usecases.customer.Customer;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CustomerGatewayTest {
    private Gateway<Customer> customers;

    @Before
    public void setUp() throws Exception {
        customers = new FakeGateway();
    }

    @Test
    public void doesNotHaveIdsWhenEmpty() {
        assertFalse(customers.existsWithId("id"));
        assertFalse(customers.existsWithId("any"));
        assertFalse(customers.existsWithId("0"));
        assertFalse(customers.existsWithId("1"));
        assertFalse(customers.existsWithId("2"));
    }

    @Test
    public void afterPersistingMustComeUpWithNewId() {
        Customer customer = makeCustomer();
        assertTrue(customer.getId().isEmpty());
        customer = customers.persist(customer);
        assertFalse(customer.getId().isEmpty());
    }

    @Test
    public void afterPersistingMustHaveId() {
        Customer customer = makeCustomer();
        customer = customers.persist(customer);
        assertTrue(customers.existsWithId(customer.getId()));
        assertFalse(customers.existsWithId("any-other"));
    }

    @Test
    public void afterPersistingMustReturnSameValues() {
        Customer original = makeCustomer();
        original = customers.persist(original);
        Customer retrieved = customers.findById(original.getId());
        assertEquals(original.getId(), retrieved.getId());
        assertEquals(original.getFirstName(), retrieved.getFirstName());
        assertEquals(original.getLastName(), retrieved.getLastName());
    }

    @Test
    public void throwsExceptionWhenGettingUnknownId() {
        try {
            customers.findById("1");
            fail();
        } catch (NoSuchEntityException exception) {
            assertEquals("1", exception.getId());
        }
    }

    private Customer makeCustomer() {
        return Customer.create().withFirstName("First").withLastName("Last");
    }
}
