package hm.entities;

public class Product implements Identifiable<Product> {
    private String id;
    private String name;
    private String description;
    private String pictureURI;
    private double price;
    private int numberOfUnits;

    private Product(String id, String name, String description, String pictureURI, double price, int numberOfUnits) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pictureURI = pictureURI;
        this.price = price;
        this.numberOfUnits = numberOfUnits;
    }

    public static Product create() {
        return new Product("", "", "", "", 0.0, 0);
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

    public double getPrice() {
        return price;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public Product withId(String id) {
        return new Product(id, name, description, pictureURI, price, numberOfUnits);
    }

    public Product withName(String name) {
        return new Product(id, name, description, pictureURI, price, numberOfUnits);
    }

    public Product withDescription(String description) {
        return new Product(id, name, description, pictureURI, price, numberOfUnits);
    }

    public Product withPictureURI(String pictureURI) {
        return new Product(id, name, description, pictureURI, price, numberOfUnits);
    }

    public Product withPrice(double price) {
        return new Product(id, name, description, pictureURI, price, numberOfUnits);
    }

    public Product withMoreUnits(int numberOfUnits) {
        return new Product(id, name, description, pictureURI, price, this.numberOfUnits + numberOfUnits);
    }

    public Product withLessUnits(int numberOfUnits) {
        return new Product(id, name, description, pictureURI, price, this.numberOfUnits - numberOfUnits);
    }
}
