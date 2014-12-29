package hm.usecases;

import hm.boundaries.persistence.Identifiable;
import hm.boundaries.persistence.Memory;

import java.util.HashMap;
import java.util.Map;

class FakeMemory<E extends Identifiable<E>> implements Memory<E> {
    private Map<String, E> map = new HashMap<>();
    private int incrementalId;

    public boolean existsWithId(String id) {
        return map.containsKey(id);
    }

    public E persist(E identifiable) {
        if (identifiable.getId().isEmpty())
            identifiable = identifiable.withId(nextId());
        map.put(identifiable.getId(), identifiable);
        return identifiable;
    }

    public E findById(String id) {
        if (!existsWithId(id)) throw new NoSuchEntityException(id);
        return map.get(id);
    }

    private String nextId() {
        return String.valueOf(++incrementalId);
    }
}
