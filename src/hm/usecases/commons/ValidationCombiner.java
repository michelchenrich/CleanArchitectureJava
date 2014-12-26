package hm.usecases.commons;

public class ValidationCombiner implements Validation {
    private Validation[] validations;

    public ValidationCombiner(Validation... validations) {
        this.validations = validations;
    }

    public boolean isOK() {
        for (Validation validation : validations)
            if (!validation.isOK()) return false;
        return true;
    }

    public void sendErrorsTo(Object output) {
        for (Validation validation : validations)
            validation.sendErrorsTo(output);
    }
}
