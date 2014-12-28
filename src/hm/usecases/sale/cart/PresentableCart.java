package hm.usecases.sale.cart;

import com.google.common.collect.ImmutableList;
import hm.entities.Gateway;
import hm.entities.Item;
import hm.entities.Product;

import java.util.List;

public class PresentableCart {
    private List<Item> items;
    private Gateway<Product> productGateway;

    public PresentableCart(List<Item> items, Gateway<Product> productGateway) {
        this.items = items;
        this.productGateway = productGateway;
    }

    public List<PresentableItem> getItems() {
        ImmutableList.Builder<PresentableItem> builder = ImmutableList.builder();
        for (Item item : items) builder.add(makePresentable(item));
        return builder.build();
    }

    private PresentableItem makePresentable(Item item) {
        return new PresentableItem(item, productGateway.findById(item.getProductId()));
    }
}
