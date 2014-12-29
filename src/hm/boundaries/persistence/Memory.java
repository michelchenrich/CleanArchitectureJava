package hm.boundaries.persistence;

public interface Memory<E extends Identifiable> {
    boolean existsWithId(String id);
    E persist(E identifiable);
    E findById(String id);
}
