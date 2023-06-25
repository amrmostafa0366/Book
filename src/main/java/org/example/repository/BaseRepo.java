package org.example.repository;

import org.example.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface BaseRepo<T extends BaseEntity,ID extends Number> extends JpaRepository<T, ID>  {

    @Query("select case when count(t) > 0 then true else false end from #{#entityName} t where t.name = :name")
    boolean existsByName(@Param("name") String name);

}
