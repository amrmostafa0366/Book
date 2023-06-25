package org.example.service.imp;

import jakarta.persistence.MappedSuperclass;
import org.example.model.BaseEntity;
import org.example.service.BaseService;
import org.example.repository.BaseRepo;
import org.example.error.ConflictException;
import org.example.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@MappedSuperclass
public class BaseServiceImp<T extends BaseEntity<ID>, ID extends Number> implements BaseService<T,ID> {

    @Autowired
    private BaseRepo<T, ID> baseRepo;


    @Override
    public T findById(ID id) {
        if (existById(id)) {
            return baseRepo.findById(id).get();
        } else {
            throw new NotFoundException(String.format("There Is No Record In Our Database Match That Id : %s", id));
        }
    }

    @Override
    public Page<T> findAll(Pageable page) {
        return baseRepo.findAll(page);
    }

    @Override
    public List<T> findAll() {
        return baseRepo.findAll();
    }

    @Override
    public T insert(T t) {
        if (existByName(t.getName())) {
            throw new ConflictException("This Author Is Already Found In Our DataBase");
        }
        return baseRepo.save(t);
    }


    @Override
    public void delete(ID id) {
        if (existById(id)) {
            baseRepo.deleteById(id);
        } else {
            throw new NotFoundException(String.format("There Is No Record In Our Database Match That %s Id", id));
        }
    }


    @Override
    public Pageable paginationCheck(Optional<Integer> pageSize, Optional<Integer> pageNumber, Optional<String> sortBy, String entity) {
        if ((pageNumber.isPresent() && pageNumber.get() < 0) ||
                (pageSize.isPresent() && pageSize.get() < 1)
        ) {
            return null;
        }
        Sort sort;
        if (sortBy.isPresent()) {
            if (isValidProperty(sortBy.get(), entity)) {
                sort = Sort.by(sortBy.get());
            } else {
                return null;
            }
        } else {
            sort = Sort.by("id");
        }

        return PageRequest.of(pageNumber.orElse(0), pageSize.orElse(10), sort);
    }

    @Override
    public boolean isValidProperty(String property, String entity) {
        try {
            entity.getClass().getDeclaredField(property);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }


    @Override
    public boolean existById(ID id) {
        return baseRepo.existsById(id);
    }

    @Override
    public boolean existByName(String name) {
        return baseRepo.existsByName(name);
    }

}
