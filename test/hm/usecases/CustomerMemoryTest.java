package hm.usecases;

import hm.domain.Customer;
import hm.domain.Memory;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CustomerMemoryTest {
    private Memory<Customer> customers;

    @Before
    public void setUp() throws Exception {
        customers = new FakeMemory();
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

    @Test(expected = NoSuchEntityException.class)
    public void throwsExceptionWhenGettingUnknownId() {
        customers.findById("1");
    }

    @Test
    public void theExceptionMustCarryTheUnknownId() {
        try {
            customers.findById("1");
        } catch (NoSuchEntityException exception) {
            assertEquals("1", exception.getId());
        }
    }

    private Customer makeCustomer() {
        return Customer.create().withFirstName("First").withLastName("Last");
    }
}
