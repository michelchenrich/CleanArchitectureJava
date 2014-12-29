package hm.usecases;

import hm.boundaries.delivery.IdBasedRequest;
import hm.boundaries.delivery.customer.PersistCustomerRequest;
import hm.boundaries.delivery.customer.UpdateCustomerRequest;
import hm.boundaries.delivery.product.AddUnitsRequest;
import hm.boundaries.delivery.product.PersistProductRequest;
import hm.boundaries.delivery.product.UpdateProductRequest;
import hm.boundaries.delivery.sale.cart.AddToCartRequest;
import hm.boundaries.delivery.sale.cart.ChangeCartRequest;

class FakeRequest implements IdBasedRequest, PersistCustomerRequest, UpdateCustomerRequest, PersistProductRequest, AddUnitsRequest, UpdateProductRequest, AddToCartRequest, ChangeCartRequest {
    public String id;
    public String firstName;
    public String lastName;
    public String name;
    public String description;
    public String pictureURI;
    public int numberOfUnits;
    public double price;
    public String customerId;
    public String productId;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPictureURI() {
        return pictureURI;
    }

    public double getPrice() {
        return price;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProductId() {
        return productId;
    }
}
