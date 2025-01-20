package com.projects.book_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Book {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String author;
}
