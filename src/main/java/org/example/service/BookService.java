package org.example.service;

import org.example.model.Book;
import org.example.model.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface BookService extends BaseService<Book,Long> {


    BookDTO findByIdDTO(Long id);

    Page<BookDTO> findAllDTO(Pageable page);

    Book insert(Book book);

    Book update(Long id, Book book);
}
