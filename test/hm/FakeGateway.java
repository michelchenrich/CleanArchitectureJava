package hm;

import hm.usecases.Gateway;
import hm.usecases.Identifiable;
import hm.usecases.NoSuchEntityException;

import java.util.HashMap;
import java.util.Map;

class FakeGateway<TEntity extends Identifiable<TEntity>> implements Gateway<TEntity> {
    private Map<String, TEntity> entities = new HashMap<>();
    private int incrementalId;

    public boolean containsWithId(String id) {
        return entities.containsKey(id);
    }

    public TEntity persist(TEntity customer) {
        if (customer.getId().isEmpty())
            customer = customer.withId(nextId());
        entities.put(customer.getId(), customer);
        return customer;
    }

    public TEntity findById(String id) {
        if (!containsWithId(id)) throw new NoSuchEntityException(id);
        return entities.get(id);
    }

    private String nextId() {
        return String.valueOf(++incrementalId);
    }
}
