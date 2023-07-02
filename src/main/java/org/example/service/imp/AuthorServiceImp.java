package org.example.service.imp;

import org.example.repository.BaseRepo;
import org.example.error.ConflictException;
import org.example.error.NotAcceptableException;
import org.example.error.NotFoundException;
import org.example.model.Author;
import org.example.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImp extends BaseServiceImp<Author, Long> implements AuthorService {

    @Autowired
    private BaseRepo<Author, Long> baseRepo;

    Logger logger = LoggerFactory.getLogger(AuthorServiceImp.class);

    @Override
    public Author update(Long id, Author author) {
        if (existById(id)) {
            Author current = baseRepo.findById(id).get();
            if (existByName(author.getName())) {
                throw new ConflictException("This Author Is Already Found In Our DataBase");
            } else if (author.getName().isEmpty() || author.getName() == null) {
                throw new NotAcceptableException("Invalid");
            } else {
                current.setName(author.getName());
                return baseRepo.save(current);
            }
        } else {
            throw new NotFoundException(String.format("There Is No Record In Our Database Match That %s Id", id));
        }
    }

}
