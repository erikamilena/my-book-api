package com.projects.book_api.repositories;

import com.projects.book_api.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository is an interface with access to data
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    //Method to search by title (partially search)
    List<Book> findByTitleContaining(String title);
}
