package hm.usecases.sale.order;

import hm.domain.Memory;
import hm.domain.Product;
import hm.domain.SaleOrder;
import hm.usecases.sale.SalePresenter;

public class SaleOrderPresenter extends SalePresenter<SaleOrder, PresentableSaleOrder> {
    public SaleOrderPresenter(Memory<Product> memory) {
        super(memory);
    }

    protected PresentableSaleOrder asPresentable(SaleOrder order) {
        return new PresentableSaleOrder(asPresentable(order.getItems()), order.isCanceled());
    }
}
