package hm.usecases.commons;

import hm.usecases.UseCase;

public class ValidatedUseCase implements UseCase {
    private UseCase useCase;
    private Object responder;
    private Validation validation;

    public ValidatedUseCase(UseCase useCase, Object responder, Validation validation) {
        this.useCase = useCase;
        this.responder = responder;
        this.validation = validation;
    }

    public void execute() {
        if (validation.isOK()) useCase.execute();
        else validation.sendErrorsTo(responder);
    }
}
