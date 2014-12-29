package hm.usecases;

import hm.domain.Identifiable;
import hm.domain.Memory;

import java.util.HashMap;
import java.util.Map;

class FakeMemory<TEntity extends Identifiable<TEntity>> implements Memory<TEntity> {
    private Map<String, TEntity> entities = new HashMap<>();
    private int incrementalId;

    public boolean existsWithId(String id) {
        return entities.containsKey(id);
    }

    public TEntity persist(TEntity customer) {
        if (customer.getId().isEmpty())
            customer = customer.withId(nextId());
        entities.put(customer.getId(), customer);
        return customer;
    }

    public TEntity findById(String id) {
        if (!existsWithId(id)) throw new NoSuchEntityException(id);
        return entities.get(id);
    }

    private String nextId() {
        return String.valueOf(++incrementalId);
    }
}
