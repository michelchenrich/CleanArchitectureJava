package hm.usecases.commons;

public interface Identifiable<TEntity extends Identifiable> {
    String getId();

    TEntity withId(String id);
}