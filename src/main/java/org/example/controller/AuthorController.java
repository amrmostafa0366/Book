package org.example.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.example.model.Author;
import org.example.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Validated
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable("id") @Min(value = 1) @Max(value = 100) Long id) {
        Author result = authorService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<Author>> findAll(@RequestParam("pageSize") Optional<Integer> pageSize,
                                                @RequestParam("pageNumber") Optional<Integer> pageNumber,
                                                @RequestParam("sortBy") Optional<String> sortBy
    ) {
        Pageable page = authorService.paginationCheck(pageSize, pageNumber, sortBy, "Author");
        if ((page == null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Page<Author> result = authorService.findAll(page);
        if (result != null && !result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
    }

    @PostMapping("")
    public ResponseEntity<Author> insert(@Valid @RequestBody Author author) {
        Author result = authorService.insert(author);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id,@Valid @RequestBody Author author) {
        Author result = authorService.update(id, author);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
