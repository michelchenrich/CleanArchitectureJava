package hm.usecases.commons;

import hm.domain.Memory;

public class IdentityValidation implements Validation {
    private Memory memory;
    private String id;
    private IdentityResponder responder;

    public IdentityValidation(Memory memory, String id, IdentityResponder responder) {
        this.memory = memory;
        this.id = id;
        this.responder = responder;
    }

    public IdentityValidation(Memory memory, IdBasedRequest request, IdentityResponder responder) {
        this(memory, request.getId(), responder);
    }

    public boolean hasErrors() {
        return !memory.existsWithId(id);
    }

    public void sendErrors() {
        if (!memory.existsWithId(id))
            responder.invalidId(id);
    }
}
