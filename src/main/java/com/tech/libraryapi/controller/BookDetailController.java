package com.tech.libraryapi.controller;

import com.tech.libraryapi.model.Book;
import com.tech.libraryapi.service.BookDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@Validated
public class BookDetailController {
    private final BookDetailService bookDetailService;

    public BookDetailController(BookDetailService bookDetailService) {
        this.bookDetailService = bookDetailService;
    }

    @PostMapping("book")
    ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        Book addedBook = bookDetailService.addBook(book);
        return new ResponseEntity<>(addedBook, HttpStatus.CREATED);
    }

    @GetMapping("books")
    ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookDetailService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("book/isbn/{isbn}")
    ResponseEntity<Book> getBookByIsbn(@Size(min = 10, max = 13, message = "{book.isbn.size}")
                                       @PathVariable(value="isbn") String isbn) {
        Book book = bookDetailService.getBookByIsbn(isbn);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
