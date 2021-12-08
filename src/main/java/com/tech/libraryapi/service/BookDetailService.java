package com.tech.libraryapi.service;

import com.tech.libraryapi.model.Book;

import java.util.List;

public interface BookDetailService {
    List<Book> getAllBooks();
    Book addBook(Book book);
    Book getBookByIsbn(String isbn);
}
