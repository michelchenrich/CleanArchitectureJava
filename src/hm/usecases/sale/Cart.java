package hm.usecases.sale;

import com.google.common.collect.ImmutableList;
import hm.usecases.product.Product;

import java.util.List;

public class Cart {
    private List<Item> items;

    public static Cart create() {
        return new Cart(ImmutableList.of());
    }

    private Cart(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getNumberOfUnitsOf(Product product) {
        for (Item item : items)
            if (item.getProductId().equals(product.getId()))
                return item.getNumberOfUnits();
        return 0;
    }

    public Cart withItem(Product product, int numberOfUnits) {
        ImmutableList.Builder<Item> builder = ImmutableList.builder();
        Item newItem = new Item(product, 0);
        for (Item item : items)
            if (item.getProductId().equals(product.getId()))
                newItem = item;
            else
                builder.add(item);
        builder.add(newItem.withMoreUnits(numberOfUnits));
        List<Item> newItems = builder.build();
        return new Cart(newItems);
    }

    public Cart withoutItem(Product product) {
        ImmutableList.Builder<Item> builder = ImmutableList.builder();
        for (Item item : items)
            if (!item.getProductId().equals(product.getId()))
                builder.add(item);
        List<Item> newItems = builder.build();
        return new Cart(newItems);
    }
}
