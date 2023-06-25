package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.Book;
import org.example.model.dto.BookDTO;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

//    @GetMapping("/{id}")
//    public ResponseEntity<Book> findById(@PathVariable("id") Long id) {
//        Book result = bookService.findById(id);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable("id") Long id) {
        BookDTO result = bookService.findByIdDTO(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<BookDTO>> findAll(@RequestParam("pageSize") Optional<Integer> pageSize,
                                                @RequestParam("pageNumber") Optional<Integer> pageNumber,
                                                @RequestParam("sortBy") Optional<String> sortBy
    ) {
        Pageable page = bookService.paginationCheck(pageSize, pageNumber, sortBy, "Book");
        if ((page == null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Page<BookDTO> result = bookService.findAllDTO(page);
        if (result != null && !result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
    }

    @PostMapping("")
    public ResponseEntity<Book> insert(@Valid @RequestBody Book book) {
        Book result = bookService.insert(book);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody Book book) {
        Book result = bookService.update(id, book);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
