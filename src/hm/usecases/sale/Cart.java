package hm.usecases.sale;

import com.google.common.collect.ImmutableList;
import hm.usecases.product.Product;

import java.util.List;

public class Cart {
    private List<CartItem> items;

    public static Cart create() {
        return new Cart(ImmutableList.of());
    }

    private Cart(List<CartItem> items) {
        this.items = items;
    }

    public Cart withNewItem(Product product, int numberOfUnits) {
        ImmutableList.Builder<CartItem> builder = ImmutableList.builder();
        builder.addAll(items);
        builder.add(new CartItem(product, numberOfUnits));
        return new Cart(builder.build());
    }

    public List<CartItem> getItems() {
        return items;
    }
}
