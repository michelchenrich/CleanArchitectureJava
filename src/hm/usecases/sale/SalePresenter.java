package hm.usecases.sale;

import com.google.common.collect.ImmutableList;
import hm.domain.Gateway;
import hm.domain.Item;
import hm.domain.Product;
import hm.domain.Sale;

import java.util.List;

public class SalePresenter {
    private Gateway<Product> productGateway;

    public SalePresenter(Gateway<Product> productGateway) {
        this.productGateway = productGateway;
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
        Product product = productGateway.findById(item.getProductId());
        return new PresentableItem(item, product);
    }
}
