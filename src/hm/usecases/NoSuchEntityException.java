package hm.usecases;

public class NoSuchEntityException extends RuntimeException {
    private String key;

    public NoSuchEntityException(String id) {
        this.key = id;
    }

    public String getId() {
        return key;
    }
}
