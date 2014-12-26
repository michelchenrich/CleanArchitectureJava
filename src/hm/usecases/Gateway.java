package hm.usecases;

import hm.usecases.commons.Identifiable;

public interface Gateway<TEntity extends Identifiable> {
    boolean containsWithId(String key);

    TEntity persist(TEntity identifiable);

    TEntity findById(String key);
}
