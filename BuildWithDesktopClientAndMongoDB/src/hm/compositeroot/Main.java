package hm.compositeroot;

import hm.boundaries.delivery.customer.CustomerUseCaseFactory;
import hm.boundaries.delivery.product.ProductUseCaseFactory;
import hm.boundaries.delivery.sale.cart.CartUseCaseFactory;
import hm.boundaries.delivery.sale.order.SaleOrderUseCaseFactory;
import hm.desktopclient.View;
import hm.desktopclient.ViewFactory;
import hm.mongodb.CustomerMemory;
import hm.mongodb.ProductMemory;
import hm.mongodb.SaleOrderMemory;

public class Main {
    public static void main(String[] arguments) {
        CustomerMemory customerMemory = new CustomerMemory();
        ProductMemory productMemory = new ProductMemory();
        SaleOrderMemory saleOrderMemory = new SaleOrderMemory();
        CustomerUseCaseFactory customerUseCaseFactory = new CustomerUseCaseFactory(customerMemory);
        ProductUseCaseFactory productUseCaseFactory = new ProductUseCaseFactory(productMemory);
        CartUseCaseFactory cartUseCaseFactory = new CartUseCaseFactory(customerMemory, productMemory);
        SaleOrderUseCaseFactory saleUseCaseFactory = new SaleOrderUseCaseFactory(customerMemory, productMemory, saleOrderMemory);
        ViewFactory clientFactory = new ViewFactory(customerUseCaseFactory, productUseCaseFactory, cartUseCaseFactory, saleUseCaseFactory);
        View client = clientFactory.makeMain();
        client.start();
    }
}
