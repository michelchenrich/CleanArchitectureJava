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

    public List<CartItem> getItems() {
        return items;
    }

    public int getNumberOfUnitsOf(Product product) {
        for (CartItem item : items)
            if (item.getProductId().equals(product.getId()))
                return item.getNumberOfUnits();
        return 0;
    }

    public Cart withItem(Product product, int numberOfUnits) {
        ImmutableList.Builder<CartItem> builder = ImmutableList.builder();
        CartItem newItem = new CartItem(product, 0);
        for (CartItem item : items)
            if (item.getProductId().equals(product.getId()))
                newItem = item;
            else
                builder.add(item);
        builder.add(newItem.withMoreUnits(numberOfUnits));
        List<CartItem> newItems = builder.build();
        return new Cart(newItems);
    }

    public Cart withoutItem(Product product) {
        ImmutableList.Builder<CartItem> builder = ImmutableList.builder();
        for (CartItem item : items)
            if (!item.getProductId().equals(product.getId()))
                builder.add(item);
        List<CartItem> newItems = builder.build();
        return new Cart(newItems);
    }
}
