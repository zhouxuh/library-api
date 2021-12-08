package com.tech.libraryapi.service;

import com.tech.libraryapi.exception.BookAlreadyExistsException;
import com.tech.libraryapi.exception.BookNotFoundException;
import com.tech.libraryapi.model.Book;
import com.tech.libraryapi.repository.BookDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDetailServiceImpl implements BookDetailService {
    private final BookDetailRepository bookDetailRepository;

    public BookDetailServiceImpl(BookDetailRepository bookDetailRepository) {
        this.bookDetailRepository = bookDetailRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = (List<Book>) bookDetailRepository.findAll();
        if (bookList.isEmpty()) throw new BookNotFoundException();
        return bookList;
    }

    @Override
    public Book addBook(Book book) throws BookAlreadyExistsException {
        String isbn = book.getIsbn();
        book.setId(null);
        if (bookDetailRepository.findBookByIsbn(isbn).isPresent()) {
            throw new BookAlreadyExistsException(isbn);
        }
        return bookDetailRepository.save(book);
    }

    @Override
    public Book getBookByIsbn(String isbn) throws BookNotFoundException {
        return bookDetailRepository.findBookByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }
}
