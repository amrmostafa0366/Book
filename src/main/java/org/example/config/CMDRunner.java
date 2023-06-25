package org.example.config;

import org.example.model.Author;
import org.example.model.Book;
import org.example.service.AuthorService;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CMDRunner implements CommandLineRunner {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;
    private static final String[] PART_ONE_A = {"Amr", "Mohamed", "Ahmed", "Youssef", "Saif", "Ayman", "Mostafa", "Hossam"};
    private static final String[] PART_TWO_A = {"Karim", "Amir", "Hussain", "Badr", "Cairo", "Lombok", "Younes", "Gad", "Emad"};
    private static final String[] PART_ONE_B = {"Flutter", "Python", "Java", "Database", "Design", "SpringBoot", "Nana++", "Software"};
    private static final String[] PART_TWO_B = {"Life", "Pattern", "Hacking", "Juice", "Meal", "Failure", "Dessert", "Version 5.0", "PowerFull"};

    public String generateRandomName(String[] a, String[] b) {
        Random random = new Random();
        String partOne = a[random.nextInt(a.length)];
        String partTwo = b[random.nextInt(b.length)];
        return partOne + " " + partTwo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (authorService.findAll().isEmpty()) {
            for (Long i = 1L; i < 5; i++) {
                Author author = new Author();
                author.setName(generateRandomName(PART_ONE_A, PART_TWO_A));
                authorService.insert(author);
                Book book = new Book();
                book.setName(generateRandomName(PART_ONE_B, PART_TWO_B));
                book.setPrice(10.0 * Math.pow(i, 2));
                book.setAuthor(authorService.findById(i));
                bookService.insert(book);
            }
        }
    }
}