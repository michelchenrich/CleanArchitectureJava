package hm.usecases.sale;

import com.google.common.collect.ImmutableList;
import hm.usecases.product.Product;

import java.util.List;

public class CustomerCart {
    private List<CustomerCartItem> items;

    public static CustomerCart create() {
        return new CustomerCart(ImmutableList.of());
    }

    private CustomerCart(List<CustomerCartItem> items) {
        this.items = items;
    }

    public CustomerCart withNewItem(Product product, int numberOfUnits) {
        ImmutableList.Builder<CustomerCartItem> builder = ImmutableList.builder();
        builder.addAll(items);
        builder.add(new CustomerCartItem(product, numberOfUnits));
        return new CustomerCart(builder.build());
    }

    public List<CustomerCartItem> getItems() {
        return items;
    }
}
