package org.example.repository;

import org.example.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends BaseRepo<Book, Long> {

}
