package com.evil.inc.icresco.service.mapper;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @param <E> The entity object
 * @param <D> The dto object
 */
@FunctionalInterface
public interface Mapper<E, D> extends Function<E, D> {
    D map(E entity);

    @Override
    default D apply(E e) {
        return map(e);
    }

    default List<D> map(Collection<? extends E> entities) {
        return entities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
