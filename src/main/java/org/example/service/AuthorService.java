package org.example.service;

import org.example.model.Author;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService extends BaseService<Author,Long> {

    Author update(Long id, Author author);
}
