package hm.usecases.commons;

public interface Validation {
    boolean isOK();

    void sendErrorsTo(Object output);
}
