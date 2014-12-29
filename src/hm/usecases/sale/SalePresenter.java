package hm.usecases.sale;

import com.google.common.collect.ImmutableList;
import hm.domain.Item;
import hm.domain.Memory;
import hm.domain.Product;
import hm.domain.Sale;

import java.util.List;

public class SalePresenter {
    private Memory<Product> productMemory;

    public SalePresenter(Memory<Product> productMemory) {
        this.productMemory = productMemory;
    }

    public PresentableSale present(Sale sale) {
        return asPresentable(sale);
    }

    private PresentableSale asPresentable(Sale sale) {
        return new PresentableSale(asPresentable(sale.getItems()));
    }

    private List<PresentableItem> asPresentable(List<Item> items) {
        ImmutableList.Builder<PresentableItem> builder = ImmutableList.builder();
        for (Item item : items)
            builder.add(asPresentable(item));
        return builder.build();
    }

    private PresentableItem asPresentable(Item item) {
        Product product = productMemory.findById(item.getProductId());
        return new PresentableItem(item, product);
    }
}
