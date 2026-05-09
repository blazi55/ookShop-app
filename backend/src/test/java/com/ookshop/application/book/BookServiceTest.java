package com.ookshop.application.book;

import com.ookshop.application.tables.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class BookServiceTest {

	@Mock
	private BookRepository bookRepository;

	@Mock
	private BookMapper bookMapper;

	@InjectMocks
	private BookService bookService;

	// =========================
	// GET SINGLE BOOK
	// =========================

	@Test
	void shouldReturnBook() {
		Book book = Book.builder()
				.id(1L)
				.name("Test")
				.price(100.0)
				.build();

		BookDto dto = new BookDto();

		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		when(bookMapper.toDto(book)).thenReturn(dto);

		BookDto result = bookService.getBook(1L);

		assertNotNull(result);
		verify(bookRepository).findById(1L);
	}

	@Test
	void shouldThrowWhenBookNotFound() {
		when(bookRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class,
				() -> bookService.getBook(1L));
	}

	// =========================
	// GET ALL BOOKS
	// =========================

	@Test
	void shouldReturnAllBooks() {
		List<Book> books = List.of(
				Book.builder().id(1L).build(),
				Book.builder().id(2L).build()
		);

		List<BookDto> dtos = List.of(new BookDto(), new BookDto());

		when(bookRepository.findAll()).thenReturn(books);
		when(bookMapper.toDtoList(books)).thenReturn(dtos);

		List<BookDto> result = bookService.getBooks();

		assertEquals(2, result.size());
		verify(bookRepository).findAll();
	}

	// =========================
	// CREATE BOOK
	// =========================

	@Test
	void shouldCreateBook() {
		CreateBookDto dto = new CreateBookDto();
		dto.setName("Book");
		dto.setPrice(50.0);

		BookDto mappedDto = new BookDto();

		when(bookMapper.toDto(any(Book.class))).thenReturn(mappedDto);

		BookDto result = bookService.createBook(dto);

		assertNotNull(result);

		ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
		verify(bookRepository).save(captor.capture());

		Book savedBook = captor.getValue();
		assertEquals("Book", savedBook.getName());
		assertEquals(50.0, savedBook.getPrice());
	}
}