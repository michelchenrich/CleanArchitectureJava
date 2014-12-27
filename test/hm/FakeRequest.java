package hm;

import hm.usecases.commons.IdBasedRequest;
import hm.usecases.customer.CustomerPersistenceRequest;
import hm.usecases.customer.UpdateCustomerRequest;
import hm.usecases.product.AddProductUnitRequest;
import hm.usecases.product.PersistProductRequest;
import hm.usecases.product.UpdateProductRequest;
import hm.usecases.sale.AddProductToCartRequest;
import hm.usecases.sale.CartMovementRequest;

class FakeRequest implements IdBasedRequest, CustomerPersistenceRequest, UpdateCustomerRequest, PersistProductRequest, AddProductUnitRequest, UpdateProductRequest, AddProductToCartRequest, CartMovementRequest {
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
