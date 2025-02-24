package com.projects.book_api.services;

import com.projects.book_api.models.Book;
import com.projects.book_api.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service is where all the business logic
 */
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        if (bookRepository.existsById(id)) {
            bookDetails.setId(id);
            return bookRepository.save(bookDetails);
        }
        return null;
    }

    public Book updateBookPath(Long id, Book bookPath) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            if (bookPath.getTitle() != null) {
                book.setTitle(bookPath.getTitle());
            }

            if (bookPath.getAuthor() != null) {
                book.setAuthor(bookPath.getAuthor());
            }

            return bookRepository.save(book);
        }
        return null;
    }

    public void deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new RuntimeException("Book not found");
        }
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

}
