package hm.usecases.commons;

import hm.usecases.Gateway;

public class IdentityValidation implements Validation {
    private Gateway gateway;
    private IdBasedRequest request;
    private IdentityResponder responder;

    public IdentityValidation(Gateway gateway, IdBasedRequest request, IdentityResponder responder) {
        this.gateway = gateway;
        this.request = request;
        this.responder = responder;
    }

    public boolean hasErrors() {
        return !gateway.containsWithId(request.getId());
    }

    public void sendErrors() {
        responder.invalidId(request.getId());
    }
}
