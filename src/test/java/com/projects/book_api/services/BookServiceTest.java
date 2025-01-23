package com.projects.book_api.services;

import com.projects.book_api.models.Book;
import com.projects.book_api.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * unit test structure: given, when and then
 * @SpringBootTest -> This notation start all Spring
 * @ExtendWith(MockitoExtension.class) -> Only start test
 */

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository mockBookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testGetAll() {
        Book book1 = new Book(1, "Libro 1", "Predeterminado");

        List<Book> books = List.of(book1);
        when(mockBookRepository.findAll()).thenReturn(books);

        List<Book> actual = bookService.getAll();

        assertEquals(1, actual.size());
    }

    @Test
    void testGetById() {
        Optional<Book> book = Optional.of(new Book(2, "Book 1", "Predeterminado 1"));
        when(mockBookRepository.findById(2L)).thenReturn(book);

        Book actual = bookService.getById(2L);

        assertNotNull(actual);
        assertEquals("Book 1", actual.getTitle());
    }

    @Test
    void testGetById_BookNotFound() {
        Long bookId = 1L;
        when(mockBookRepository.findById(bookId)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookService.getById(bookId);
        });

        assertEquals("Book not found", exception.getMessage());
    }

    @Test
    void createBook() {
        Book book = new Book(1, "First Book","Juana");
        when(mockBookRepository.save(book)).thenReturn(book); //simulate that repo save a book and return

        Book createdBook = bookService.createBook(book); //call to method create book

        assertNotNull(createdBook);
        assertEquals("First Book", createdBook.getTitle()); //Verify that book was saved and the title check
    }

    @Test
    void testUpdateBook() {
        Book updateDetails = new Book(1, "First Book - Edicion Especial", "Juana");

        when(mockBookRepository.existsById(1L)).thenReturn(true);
        when(mockBookRepository.save(updateDetails)).thenReturn(updateDetails);

        Book updateBook = bookService.updateBook(1L, updateDetails);

        assertNotNull(updateBook);
        assertEquals("First Book - Edicion Especial", updateBook.getTitle());
    }

    @Test
    void testUpdateBook_NotExisting() {
        Book updateBookNotExisting = new Book(5, "First Book- New Edition", "Juana");

        when(mockBookRepository.existsById(5L)).thenReturn(false);

        Book actualBook = bookService.updateBook(5L, updateBookNotExisting);

        assertEquals(null, actualBook);
    }

    @Test
    void testDeleteBook() {
        when(mockBookRepository.existsById(1L)).thenReturn(true);

        bookService.deleteBook(1L);

        verify(mockBookRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBook_NotExissting() {
        Long bookId = 1L;
        when(mockBookRepository.findById(bookId)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
           bookService.getById(bookId);
        });

        assertEquals("Book not found", exception.getMessage());
    }
}