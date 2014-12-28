package hm.usecases.commons;

public interface Validation {
    boolean hasErrors();
    void sendErrors();
}
