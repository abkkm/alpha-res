package huh.enterprise.alpha.api.common;

import huh.enterprise.alpha.common.ApiNotImplemented;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface ApiControllerBase<K, V> {
    @GetMapping("/{id}")
    default V get(@PathVariable Long id) {
        throw new ApiNotImplemented("");
    }

    @GetMapping
    default List<V> getAll() {
        throw new ApiNotImplemented("");
    }

    @GetMapping("/search")
    default List<V> search(@Valid K query) {
        throw new ApiNotImplemented("Search not implemented");
    }

    @PostMapping
    default V create(@RequestBody @Valid V record) {
        throw new ApiNotImplemented("Create not implemented");
    }

    @PostMapping("/bulk")
    default List<V> createAll(@RequestBody @Valid List<V> records) {
        throw new ApiNotImplemented("Create not implemented");
    }

    @PutMapping("/{id}")
    default V update(@PathVariable Long id, @RequestBody @Valid V record) {
        throw new ApiNotImplemented("Update not implemented");
    }

    @PutMapping("/{id}/bulk")
    default List<V> updateAll(@PathVariable Long id, @RequestBody @Valid List<V> records) {
        throw new ApiNotImplemented("Update all not implemented");
    }

    @DeleteMapping("/{id}")
    default boolean delete(@PathVariable Long id) {
        throw new ApiNotImplemented("Delete not implemented");
    }

    @DeleteMapping("/bulk")
    default boolean deleteAllByQuery(@Valid K query) {
        throw new ApiNotImplemented("Delete all by query not implemented");
    }

    @DeleteMapping
    default boolean deleteAll() {
        throw new ApiNotImplemented("Update not implemented");
    }

}
