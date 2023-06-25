package org.example.repository;

import org.example.model.Author;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends BaseRepo<Author, Long> , JpaSpecificationExecutor<Author> {


}
