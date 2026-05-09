package com.ookshop.application.book;

import com.ookshop.application.tables.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("bookOokShop")
@CrossOrigin
public class BookController {

    private final BookService bookService;

    @GetMapping("{bookId}")
    public BookDto getBook(@PathVariable long bookId) {
        return bookService.getBook(bookId);
    }

    @GetMapping("/books")
    public List<BookDto> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping("/book")
    public BookDto createBook(@RequestBody CreateBookDto createBookDto) {
        return bookService.createBook(createBookDto);

    }
}
