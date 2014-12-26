package hm;

import hm.usecases.Gateway;
import hm.usecases.NoSuchKeyException;
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
        assertFalse(customers.containsWithId("id"));
        assertFalse(customers.containsWithId("any"));
        assertFalse(customers.containsWithId("0"));
        assertFalse(customers.containsWithId("1"));
        assertFalse(customers.containsWithId("2"));
    }

    @Test
    public void afterPuttingMustHaveId() {
        Customer customer = Customer.create().withId("id").with("First", "Last");
        customers.persist(customer);
        assertTrue(customers.containsWithId("id"));
        assertFalse(customers.containsWithId("any-other"));
    }

    @Test
    public void afterPuttingMustReturnSameValue() {
        Customer original = Customer.create().withId("id").with("First", "Last");
        customers.persist(original);
        Customer retrieved = customers.findById("id");
        assertEquals(original.getId(), retrieved.getId());
        assertEquals(original.getFirstName(), retrieved.getFirstName());
        assertEquals(original.getLastName(), retrieved.getLastName());
    }

    @Test
    public void throwsExceptionWhenGettingUnknownKey() {
        try {
            customers.findById("1");
            fail();
        } catch (NoSuchKeyException exception) {
            assertEquals("1", exception.getKey());
        }
    }
}
