package hm.entities;

public interface Gateway<TEntity extends Identifiable> {
    boolean existsWithId(String id);

    TEntity persist(TEntity entity);

    TEntity findById(String id);
}