package hm.usecases.product;

import hm.boundaries.delivery.product.PersistProductRequest;
import hm.boundaries.delivery.product.ProductDataResponder;
import hm.usecases.Validation;

public class ProductDataValidation implements Validation {
    private String name;
    private String description;
    private String pictureURI;
    private double price;
    private ProductDataResponder responder;
    private PersistProductRequest request;

    public ProductDataValidation(PersistProductRequest request, ProductDataResponder responder) {
        this.request = request;
        this.responder = responder;
    }

    public boolean hasErrors() {
        initializeFields();
        return name.isEmpty() || description.isEmpty() || pictureURI.isEmpty() || price < 0;
    }

    public void sendErrors() {
        initializeFields();
        if (name.isEmpty())
            responder.nameIsEmpty();
        if (description.isEmpty())
            responder.descriptionIsEmpty();
        if (pictureURI.isEmpty())
            responder.pictureURIIsEmpty();
        if (price < 0)
            responder.priceIsNegative();
    }

    private void initializeFields() {
        name = makeSafe(request.getName());
        description = makeSafe(request.getDescription());
        pictureURI = makeSafe(request.getPictureURI());
        price = request.getPrice();
    }

    private static String makeSafe(String input) {
        return input == null ? "" : input.trim();
    }
}
