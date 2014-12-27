package hm.usecases.commons;

import hm.usecases.UseCase;

public class ValidatedUseCase implements UseCase {
    private UseCase useCase;
    private Validation[] validations;

    public ValidatedUseCase(UseCase useCase, Validation... validations) {
        this.useCase = useCase;
        this.validations = validations;
    }

    public void execute() {
        if (hasErrors()) sendErrors();
        else useCase.execute();
    }

    private boolean hasErrors() {
        for (Validation validation : validations)
            if (validation.hasErrors()) return true;
        return false;
    }

    private void sendErrors() {
        for (Validation validation : validations)
            validation.sendErrors();
    }
}
