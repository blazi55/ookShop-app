package com.ookshop.application.book;

import com.ookshop.application.tables.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper {

    public BookDto toDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .price(book.getPrice())
                .build();
    }

    public List<BookDto> toDtoList(List<Book> book) {
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book singleBook: book) {
            BookDto bookDto =  BookDto.builder()
                    .id(singleBook.getId())
                    .name(singleBook.getName())
                    .price(singleBook.getPrice())
                    .build();
            bookDtos.add(bookDto);
        }

        return bookDtos;
    }

}
