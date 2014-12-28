package hm.domain;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class SaleOrder implements Identifiable<SaleOrder>, Sale {
    private String id;
    private List<Item> items;

    private SaleOrder(String id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public static SaleOrder create() {
        return new SaleOrder("", ImmutableList.of());
    }

    public String getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public SaleOrder withId(String id) {
        return new SaleOrder(id, items);
    }

    public SaleOrder withItem(Item item) {
        ImmutableList.Builder<Item> builder = ImmutableList.builder();
        builder.addAll(items);
        builder.add(item);
        return new SaleOrder(id, builder.build());
    }
}
