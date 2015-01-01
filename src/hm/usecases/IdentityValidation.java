package hm.usecases;

import hm.boundaries.delivery.IdBasedRequest;
import hm.boundaries.delivery.IdentityResponder;
import hm.boundaries.persistence.Memory;

public class IdentityValidation implements Validation {
    private Memory memory;
    private IdBasedRequest request;
    private IdentityResponder responder;

    public IdentityValidation(Memory memory, IdBasedRequest request, IdentityResponder responder) {
        this.memory = memory;
        this.request = request;
        this.responder = responder;
    }

    public boolean hasErrors() {
        return !memory.existsWithId(request.getId());
    }

    public void sendErrors() {
        if (!memory.existsWithId(request.getId()))
            responder.invalidId(request.getId());
    }
}
