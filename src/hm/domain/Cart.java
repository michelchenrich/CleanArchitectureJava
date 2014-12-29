package hm.domain;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Sale {
    private ImmutableMap<String, Item> itemMap;

    private Cart(Map<String, Item> itemMap) {
        this.itemMap = ImmutableMap.copyOf(itemMap);
    }

    public static Cart create() {
        return new Cart(ImmutableMap.of());
    }

    public List<Item> getItems() {
        return itemMap.values().asList();
    }

    public int getNumberOfUnitsOf(String productId) {
        if (itemMap.containsKey(productId))
            return itemMap.get(productId).getNumberOfUnits();
        return 0;
    }

    public Cart withItem(String productId, double price, int numberOfUnits) {
        Map<String, Item> items = new HashMap<>(itemMap);
        Item item = items.getOrDefault(productId, new Item(productId, price, 0));
        items.put(productId, item.withMoreUnits(numberOfUnits));
        return new Cart(items);
    }

    public Cart withoutItem(String productId) {
        Map<String, Item> items = new HashMap<>(itemMap);
        items.remove(productId);
        return new Cart(items);
    }
}