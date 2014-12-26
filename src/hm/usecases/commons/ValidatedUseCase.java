package hm.usecases.commons;

import hm.usecases.UseCase;

public class ValidatedUseCase implements UseCase {
    private UseCase useCase;
    private Object responder;
    private Validation validation;

    public ValidatedUseCase(UseCase useCase, Object responder, Validation... validations) {
        this.useCase = useCase;
        this.responder = responder;
        this.validation = new ValidationCombiner(validations);
    }

    public void execute() {
        if (validation.isOK()) useCase.execute();
        else validation.sendErrorsTo(responder);
    }

    private static class ValidationCombiner implements Validation {
        private Validation[] validations;

        public ValidationCombiner(Validation[] validations) {
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
}
