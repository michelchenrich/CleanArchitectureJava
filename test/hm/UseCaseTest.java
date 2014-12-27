package hm;

import hm.usecases.customer.Customer;
import hm.usecases.customer.CustomerUseCaseFactory;
import hm.usecases.product.Product;
import hm.usecases.product.ProductUseCaseFactory;
import hm.usecases.sale.CustomerCart;
import hm.usecases.sale.SaleUseCaseFactory;
import static org.junit.Assert.*;
import org.junit.Before;

public abstract class UseCaseTest {
    public static final double PRICE_PRECISION = .001;
    private FakeResponder responder;
    private ProductUseCaseFactory productUseCaseFactory;
    private CustomerUseCaseFactory customerUseCaseFactory;
    private SaleUseCaseFactory saleUseCaseFactory;

    @Before
    public void setUp() {
        FakeGateway<Product> productGateway = new FakeGateway<>();
        FakeGateway<Customer> customerGateway = new FakeGateway<>();
        productUseCaseFactory = new ProductUseCaseFactory(productGateway);
        customerUseCaseFactory = new CustomerUseCaseFactory(customerGateway);
        saleUseCaseFactory = new SaleUseCaseFactory(customerGateway, productGateway);
    }

    protected void addUnitToProduct(String id, int numberOfUnits) {
        FakeRequest request = new FakeRequest();
        request.id = id;
        request.numberOfUnits = numberOfUnits;
        responder = new FakeResponder();
        productUseCaseFactory.makeUnitAdder(request, responder).execute();
    }

    protected void updateProduct(String id, String name, String description, String pictureURI, double price) {
        FakeRequest request = new FakeRequest();
        request.id = id;
        request.name = name;
        request.description = description;
        request.pictureURI = pictureURI;
        request.price = price;
        responder = new FakeResponder();
        productUseCaseFactory.makeUpdater(request, responder).execute();
    }

    protected String createProduct(String name, String description, String pictureURI, double price) {
        FakeRequest request = new FakeRequest();
        request.name = name;
        request.description = description;
        request.pictureURI = pictureURI;
        request.price = price;
        responder = new FakeResponder();
        productUseCaseFactory.makeCreator(request, responder).execute();
        return responder.createdWithId;
    }

    protected Product presentProduct(String id) {
        FakeRequest request = new FakeRequest();
        request.id = id;
        responder = new FakeResponder();
        productUseCaseFactory.makePresenter(request, responder).execute();
        return responder.product;
    }

    protected Customer presentCustomer(String id) {
        FakeRequest request = new FakeRequest();
        request.id = id;
        responder = new FakeResponder();
        customerUseCaseFactory.makePresenter(request, responder).execute();
        return responder.customer;
    }

    protected CustomerCart presentCustomerCart(String id) {
        FakeRequest request = new FakeRequest();
        request.id = id;
        responder = new FakeResponder();
        saleUseCaseFactory.makeCartPresenter(request, responder).execute();
        return responder.customerCart;
    }

    protected void updateCustomer(String id, String firstName, String lastName) {
        FakeRequest request = new FakeRequest();
        request.id = id;
        request.firstName = firstName;
        request.lastName = lastName;
        responder = new FakeResponder();
        customerUseCaseFactory.makeUpdater(request, responder).execute();
    }

    protected String createCustomer(String firstName, String lastName) {
        FakeRequest request = new FakeRequest();
        request.firstName = firstName;
        request.lastName = lastName;
        responder = new FakeResponder();
        customerUseCaseFactory.makeCreator(request, responder).execute();
        return responder.createdWithId;
    }

    protected void addProductToCart(String customerId, String productId, int numberOfUnits) {
        FakeRequest request = new FakeRequest();
        request.customerId = customerId;
        request.productId = productId;
        request.numberOfUnits = numberOfUnits;
        responder = new FakeResponder();
        saleUseCaseFactory.makeCartAdder(request, responder).execute();
    }

    protected void assertFound() {
        assertFalse(responder.invalidId);
    }

    protected void assertNotFound() {
        assertTrue(responder.invalidId);
    }

    protected void assertErrorsSent(String... errorMessages) throws Exception {
        for (String errorMessage : errorMessages)
            assertTrue((Boolean) FakeResponder.class.getField(errorMessage).get(responder));
    }

    protected void assertErrorsNotSent(String... errorMessages) throws Exception {
        for (String errorMessage : errorMessages)
            assertFalse((Boolean) FakeResponder.class.getField(errorMessage).get(responder));
    }

    protected void assertProduct(String id, String name, String description, String pictureURI, double price, int numberOfUnits) {
        Product product = presentProduct(id);
        assertFound();
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(pictureURI, product.getPictureURI());
        assertEquals(price, product.getPrice(), PRICE_PRECISION);
        assertEquals(numberOfUnits, product.getNumberOfUnits());
    }

    protected void assertNothingCreated() {
        assertNull(responder.createdWithId);
    }
}
