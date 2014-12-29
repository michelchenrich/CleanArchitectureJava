package hm.domain;

public interface Memory<TEntity extends Identifiable> {
    boolean existsWithId(String id);
    TEntity persist(TEntity entity);
    TEntity findById(String id);
}
