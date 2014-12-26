package hm.usecases;

public class NoSuchEntityException extends RuntimeException {
    private String id;

    public NoSuchEntityException(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
