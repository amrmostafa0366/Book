package org.example.service.imp;

import org.example.repository.BaseRepo;
import org.example.error.ConflictException;
import org.example.error.NotAcceptableException;
import org.example.error.NotFoundException;
import org.example.model.Book;
import org.example.model.dto.BookDTO;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImp extends BaseServiceImp<Book,Long> implements BookService {

    @Autowired
    private BaseRepo<Book,Long> baseRepo;

    @Override
    public BookDTO findByIdDTO(Long id) {
        if (existById(id)) {
            return mapBookToDTO(baseRepo.findById(id).get());
        } else {
            throw new NotFoundException(String.format("There Is No Record In Our Database Match That Id : %s", id));
        }
    }

    @Override
    public Page<Book> findAll(Pageable page) {
        return baseRepo.findAll(page);
    }

    @Override
    public Page<BookDTO> findAllDTO(Pageable page) {
        return mapBooksToDTOs(baseRepo.findAll(page));
    }

    @Override
    public Book update(Long id, Book book) {
        if (existById(id)) {
            Book current = baseRepo.findById(id).get();
            if (existByName(book.getName())) {
                throw new ConflictException("This Book Is Already Found In Our DataBase");
            } else if (book.getName().isEmpty() || book.getName() == null) {
                throw new NotAcceptableException("Invalid");
            } else {
                current.setName(book.getName());
                return baseRepo.save(current);
            }
        } else {
            throw new NotFoundException(String.format("There Is No Record In Our Database Match That %s Id", id));
        }
    }

    private Page<BookDTO> mapBooksToDTOs(Page<Book> books) {
        List<BookDTO> bookDTOs = books.stream()
                .map(this::mapBookToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(bookDTOs, books.getPageable(), books.getTotalElements());

    }

    private BookDTO mapBookToDTO(Book book) {
        return new BookDTO(book.getId(), book.getName(), book.getPrice(), book.getDiscount());
    }

}
