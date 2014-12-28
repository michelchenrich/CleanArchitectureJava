package hm.usecases;

import hm.entities.Customer;
import hm.entities.Product;
import hm.entities.SaleOrder;
import hm.usecases.cart.CartUseCaseFactory;
import hm.usecases.cart.PresentableCart;
import hm.usecases.customer.CustomerUseCaseFactory;
import hm.usecases.customer.PresentableCustomer;
import hm.usecases.product.PresentableProduct;
import hm.usecases.product.ProductUseCaseFactory;
import hm.usecases.saleorder.SaleOrderUseCaseFactory;
import static org.junit.Assert.*;
import org.junit.Before;

public abstract class UseCaseTest {
    protected static final double PRICE_PRECISION = .001;
    private FakeResponder responder;
    private ProductUseCaseFactory productUseCaseFactory;
    private CustomerUseCaseFactory customerUseCaseFactory;
    private CartUseCaseFactory cartUseCaseFactory;
    private SaleOrderUseCaseFactory saleOrderUseCaseFactory;

    @Before
    public void setUp() {
        FakeGateway<Product> productGateway = new FakeGateway<>();
        FakeGateway<Customer> customerGateway = new FakeGateway<>();
        FakeGateway<SaleOrder> saleOrderGateway = new FakeGateway<>();

        productUseCaseFactory = new ProductUseCaseFactory(productGateway);
        customerUseCaseFactory = new CustomerUseCaseFactory(customerGateway);
        cartUseCaseFactory = new CartUseCaseFactory(customerGateway, productGateway);
        saleOrderUseCaseFactory = new SaleOrderUseCaseFactory(customerGateway, productGateway, saleOrderGateway);
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

    protected PresentableProduct presentProduct(String id) {
        FakeRequest request = new FakeRequest();
        request.id = id;
        responder = new FakeResponder();
        productUseCaseFactory.makePresenter(request, responder).execute();
        return responder.product;
    }

    protected PresentableCustomer presentCustomer(String id) {
        FakeRequest request = new FakeRequest();
        request.id = id;
        responder = new FakeResponder();
        customerUseCaseFactory.makePresenter(request, responder).execute();
        return responder.customer;
    }

    protected PresentableCart presentCart(String id) {
        FakeRequest request = new FakeRequest();
        request.id = id;
        responder = new FakeResponder();
        cartUseCaseFactory.makePresenter(request, responder).execute();
        return responder.cart;
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
        cartUseCaseFactory.makeProductAdder(request, responder).execute();
    }

    protected void dropProductFromCart(String customerId, String productId) {
        FakeRequest request = new FakeRequest();
        request.customerId = customerId;
        request.productId = productId;
        responder = new FakeResponder();
        cartUseCaseFactory.makeProductDropper(request, responder).execute();
    }

    protected void dropAllFromCart(String id) {
        FakeRequest request = new FakeRequest();
        request.id = id;
        responder = new FakeResponder();
        cartUseCaseFactory.makeDropper(request, responder).execute();
    }

    protected String submitOrder(String id) {
        FakeRequest request = new FakeRequest();
        request.id = id;
        responder = new FakeResponder();
        saleOrderUseCaseFactory.makeOrderSubmitter(request, responder).execute();
        return responder.createdWithId;
    }

    protected SaleOrder presentOrder(String id) {
        FakeRequest request = new FakeRequest();
        request.id = id;
        responder = new FakeResponder();
        saleOrderUseCaseFactory.makeOrderPresenter(request, responder).execute();
        return responder.order;
    }

    protected void assertFound() {
        assertFalse(responder.idIsInvalid);
    }

    protected void assertNotFound(String invalidId) {
        assertTrue(responder.idIsInvalid);
        assertEquals(invalidId, responder.invalidId);
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
        PresentableProduct product = presentProduct(id);
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

    protected String createDefaultProduct() {
        return createProduct("Name", "Description", "PictureURI", 10.0);
    }

    protected String createDefaultCustomer() {
        return createCustomer("First", "Last");
    }
}
