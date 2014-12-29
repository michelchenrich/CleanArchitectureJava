package hm.boundaries.persistence;

public interface Identifiable<T extends Identifiable> {
    String getId();
    T withId(String id);
}