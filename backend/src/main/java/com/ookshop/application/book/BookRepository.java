package com.ookshop.application.book;

import com.ookshop.application.tables.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}
