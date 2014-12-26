package hm.usecases;

public class NoSuchKeyException extends RuntimeException {
    private String key;

    public NoSuchKeyException(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
