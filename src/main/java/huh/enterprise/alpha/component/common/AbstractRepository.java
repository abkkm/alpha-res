package huh.enterprise.alpha.component.common;

import java.util.List;

import static java.util.Collections.emptyList;

public abstract class AbstractRepository<K, V> {
    protected V select(K query) {
        throw new IllegalStateException();
    }

    protected List<V> selectAll() {
        return emptyList();
    }

    protected List<V> search(K query) {
        return emptyList();
    }

    protected V insert(V record) {
        throw new IllegalStateException();
    }

    protected List<V> insertAll(List<V> records) {
        return emptyList();
    }

    protected V update(V record) {
        throw new IllegalStateException();
    }

    protected List<V> updateAll(List<V> records) {
        throw new IllegalStateException();
    }

    protected boolean delete(K query) {
        return false;
    }

    protected boolean deleteAll(K query) {
        return false;
    }

}
