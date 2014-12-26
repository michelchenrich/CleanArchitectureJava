package hm.usecases.commons;

import hm.usecases.Gateway;

public class IdentityValidation implements Validation {
    private String id;
    private Gateway gateway;

    public IdentityValidation(IdBasedRequest request, Gateway gateway) {
        id = request.getId();
        this.gateway = gateway;
    }

    public boolean isOK() {
        return gateway.containsWithId(id);
    }

    public void sendErrorsTo(Object output) {
        IdentityResponder responder = (IdentityResponder) output;
        responder.invalidId(id);
    }
}
