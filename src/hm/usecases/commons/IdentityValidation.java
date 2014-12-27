package hm.usecases.commons;

import hm.usecases.Gateway;

public class IdentityValidation implements Validation {
    private Gateway gateway;
    private String id;
    private IdentityResponder responder;

    public IdentityValidation(Gateway gateway, String id, IdentityResponder responder) {
        this.gateway = gateway;
        this.id = id;
        this.responder = responder;
    }

    public IdentityValidation(Gateway gateway, IdBasedRequest request, IdentityResponder responder) {
        this(gateway, request.getId(), responder);
    }

    public boolean hasErrors() {
        return !gateway.existsWithId(id);
    }

    public void sendErrors() {
        if (!gateway.existsWithId(id)) responder.invalidId(id);
    }
}
