package hm.boundaries.persistence;

import java.util.List;

public interface Memory<E extends Identifiable> {
    boolean existsWithId(String id);
    E persist(E identifiable);
    E findById(String id);
    List<E> findAll();
}
