package com.projects.book_api.repositories;

import com.projects.book_api.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository is an interface with access to data
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
