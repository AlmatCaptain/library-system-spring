package kz.home.librarysystem.controller;

import kz.home.librarysystem.model.Author;
import kz.home.librarysystem.model.Book;
import kz.home.librarysystem.model.BookTransaction;
import kz.home.librarysystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;


    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("")
    public void addBooks(@RequestBody List<Book> books) {
        bookService.addBooks(books);
    }

    public List<Book> getBooksByStatus(Book.BookStatus status) {
        return bookService.getBooksByStatus(status);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    public void requestBook(Long bookId, Long memberId) {
        bookService.requestBook(bookId, memberId);
    }

    public void issueBook(Long bookId, Long memberId, LocalDate dueDate) {
        bookService.issueBook(bookId, memberId, dueDate);
    }

    public void returnBookToLib(BookTransaction t) {
        bookService.returnBookToLib(t);
    }

    public List<Book> findByAuthor(Author a) {
        return bookService.findByAuthor(a);
    }

    public Book findByTitle(String name) {
        return bookService.findByTitle(name);
    }

    public Map<Long, Long> getRequestedBooks() {
        return bookService.getRequestedBooks();
    }
}
