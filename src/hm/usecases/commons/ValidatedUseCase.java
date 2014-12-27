package hm.usecases.commons;

import hm.usecases.UseCase;

public class ValidatedUseCase implements UseCase {
    private UseCase useCase;
    private Validation validation;

    public ValidatedUseCase(UseCase useCase, Validation... validations) {
        this.useCase = useCase;
        this.validation = new ValidationCombiner(validations);
    }

    public void execute() {
        if (validation.hasErrors()) validation.sendErrors();
        else useCase.execute();
    }

    private static class ValidationCombiner implements Validation {
        private Validation[] validations;

        public ValidationCombiner(Validation[] validations) {
            this.validations = validations;
        }

        public boolean hasErrors() {
            for (Validation validation : validations)
                if (validation.hasErrors()) return true;
            return false;
        }

        public void sendErrors() {
            for (Validation validation : validations)
                validation.sendErrors();
        }
    }
}
