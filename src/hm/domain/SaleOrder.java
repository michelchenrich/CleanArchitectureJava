package hm.domain;

import com.google.common.collect.ImmutableList;
import hm.boundaries.persistence.Identifiable;

import java.util.List;

public class SaleOrder implements Identifiable<SaleOrder>, Sale {
    private String id;
    private List<Item> items;
    private boolean canceled;

    private SaleOrder(String id, List<Item> items, boolean canceled) {
        this.id = id;
        this.items = items;
        this.canceled = canceled;
    }

    public static SaleOrder create() {
        return new SaleOrder("", ImmutableList.of(), false);
    }

    public String getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public SaleOrder withId(String id) {
        return new SaleOrder(id, items, canceled);
    }

    public SaleOrder withItem(Item item) {
        ImmutableList.Builder<Item> builder = ImmutableList.builder();
        builder.addAll(items);
        builder.add(item);
        return new SaleOrder(id, builder.build(), canceled);
    }

    public SaleOrder asCanceled() {
        return new SaleOrder(id, items, true);
    }
}
