package org.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public interface BaseService<T, ID> {

    T findById(ID id);

    Page<T> findAll(Pageable page);

    List<T> findAll();

    T insert(T t);

    void delete(ID id);

    Pageable paginationCheck(Optional<Integer> pageSize, Optional<Integer> pageNumber, Optional<String> sortBy, String entity);

    boolean isValidProperty(String property, String entity);

    boolean existById(ID id);

    boolean existByName(String name);
}
