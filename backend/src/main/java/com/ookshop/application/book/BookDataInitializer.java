package com.ookshop.application.book;

import com.ookshop.application.tables.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookDataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) {
        if (bookRepository.count() > 0) {
            return;
        }

        bookRepository.save(Book.builder().name("The Silent Library").price(39.90).build());
        bookRepository.save(Book.builder().name("Midnight Manuscript").price(44.50).build());
        bookRepository.save(Book.builder().name("Pages of Autumn").price(32.00).build());
        bookRepository.save(Book.builder().name("Ink & Ember").price(48.90).build());
        bookRepository.save(Book.builder().name("The Cartographer's Tale").price(54.00).build());
        bookRepository.save(Book.builder().name("Harbor of Stories").price(36.50).build());
    }
}
