package huh.enterprise.alpha.component.common;

import huh.enterprise.alpha.common.exception.ServiceNotImplemented;

import java.util.List;

public interface ServiceBase<K, V> {
    default V get(K query) {
        throw new ServiceNotImplemented("get record not implemented");
    }

    default List<V> getAll() {
        throw new ServiceNotImplemented("get all records not implemented");
    }

    default List<V> search(K query) {
        throw new ServiceNotImplemented("get all by query records not implemented");
    }

    default V create(V record) {
        throw new ServiceNotImplemented("create record not implemented");
    }

    default List<V> createAll(List<V> records) {
        throw new ServiceNotImplemented("create all records not implemented");
    }

    default V update(V record) {
        throw new ServiceNotImplemented("update record not implemented");
    }

    default List<V> updateAll(List<V> records) {
        throw new ServiceNotImplemented("update all records not implemented");
    }

    default boolean delete(K query) {
        throw new ServiceNotImplemented("delete record not implemented");
    }

    default boolean deleteAll(K query) {
        throw new ServiceNotImplemented("delete by query records not implemented");
    }
}
