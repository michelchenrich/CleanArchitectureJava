package hm.usecases.product;

import com.google.common.collect.ImmutableList;
import hm.usecases.Identifiable;

import java.util.List;

public class Product implements Identifiable<Product> {
    private String id;
    private String name;
    private String description;
    private String pictureURI;
    private List<ProductUnit> units;

    private Product(String id, String name, String description, String pictureURI, List<ProductUnit> units) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pictureURI = pictureURI;
        this.units = units;
    }

    public static Product create() {
        return new Product("", "", "", "", ImmutableList.of());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPictureURI() {
        return pictureURI;
    }

    public List<ProductUnit> getUnits() {
        return units;
    }

    public Product withId(String id) {
        return new Product(id, name, description, pictureURI, units);
    }

    public Product withName(String name) {
        return new Product(id, name, description, pictureURI, units);
    }

    public Product withDescription(String description) {
        return new Product(id, name, description, pictureURI, units);
    }

    public Product withPictureURI(String pictureURI) {
        return new Product(id, name, description, pictureURI, units);
    }

    public Product withNewUnit(double price) {
        ImmutableList.Builder<ProductUnit> builder = ImmutableList.builder();
        builder.addAll(units).add(new ProductUnit(id, price));
        return new Product(id, name, description, pictureURI, builder.build());
    }
}
