package hm.usecases.product;

import hm.usecases.commons.Validation;

public class ProductDataValidation implements Validation {
    private String name;
    private String description;
    private String pictureURI;
    private ProductDataResponder responder;

    public ProductDataValidation(PersistProductRequest request, ProductDataResponder responder) {
        name = makeSafe(request.getName());
        description = makeSafe(request.getDescription());
        pictureURI = makeSafe(request.getPictureURI());
        this.responder = responder;
    }

    public boolean hasErrors() {
        return name.isEmpty() || description.isEmpty() || pictureURI.isEmpty();
    }

    public void sendErrors() {
        if (name.isEmpty()) responder.nameIsEmpty();
        if (description.isEmpty()) responder.descriptionIsEmpty();
        if (pictureURI.isEmpty()) responder.pictureURIIsEmpty();
    }

    private static String makeSafe(String input) {
        return input == null ? "" : input.trim();
    }
}
