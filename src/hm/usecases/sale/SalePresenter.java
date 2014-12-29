package hm.usecases.sale;

import com.google.common.collect.ImmutableList;
import hm.boundaries.delivery.sale.PresentableItem;
import hm.boundaries.delivery.sale.PresentableSale;
import hm.boundaries.persistence.Memory;
import hm.domain.Item;
import hm.domain.Product;
import hm.domain.Sale;

import java.util.List;

public abstract class SalePresenter<F extends Sale, T extends PresentableSale> {
    protected Memory<Product> productMemory;

    public SalePresenter(Memory<Product> productMemory) {
        this.productMemory = productMemory;
    }

    public T present(F sale) {
        return asPresentable(sale);
    }

    protected abstract T asPresentable(F sale);

    protected List<PresentableItem> asPresentable(List<Item> items) {
        ImmutableList.Builder<PresentableItem> builder = ImmutableList.builder();
        for (Item item : items)
            builder.add(asPresentable(item));
        return builder.build();
    }

    protected PresentableItem asPresentable(Item item) {
        Product product = productMemory.findById(item.getProductId());
        return new PresentableItem(item, product);
    }
}
