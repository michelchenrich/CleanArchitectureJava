package hm.usecases;

import hm.usecases.commons.Identifiable;

public interface Gateway<TEntity extends Identifiable> {
    boolean containsWithId(String id);

    TEntity persist(TEntity identifiable);

    TEntity findById(String id);
}
