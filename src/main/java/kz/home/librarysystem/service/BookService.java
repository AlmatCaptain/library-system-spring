package kz.home.librarysystem.service;

import kz.home.librarysystem.model.Author;
import kz.home.librarysystem.model.Book;
import kz.home.librarysystem.model.BookTransaction;
import kz.home.librarysystem.repository.BookRepository;
import kz.home.librarysystem.repository.BookTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookTransactionRepository bookTransactionRepository;
    private Map<Long, Long> requestedBooks;

    @Autowired
    public BookService(BookRepository bookRepository, BookTransactionRepository bookTransactionRepository) {
        this.bookRepository = bookRepository;
        this.bookTransactionRepository = bookTransactionRepository;
        this.requestedBooks = new HashMap<>();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void addBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    public List<Book> getBooksByStatus(Book.BookStatus status) {

        if (status.equals(Book.BookStatus.OVERDUE)) {
            List<Book> books = new ArrayList<>();
            for (BookTransaction t : bookTransactionRepository.findAll()) {
                Book b = t.getBook();
                if (t.getDueDate()
                     .isBefore(LocalDate.now())) {
                    b.setBookStatus(Book.BookStatus.OVERDUE);
                    bookRepository.save(b);
                    books.add(b);
                }
            }
            return books;
        }

        return bookRepository.findAllByBookStatus(status);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                             .get();
    }

    public void requestBook(Long bookId, Long memberId) {
        Book b = getBookById(bookId);
        if (!b.getBookStatus()
              .equals(Book.BookStatus.AVAILABLE)) {
            System.out.println("Book is not available");
            return;
        }
        b.setBookStatus(Book.BookStatus.REQUESTED);
        bookRepository.save(b);
        requestedBooks.put(bookId, memberId);
    }

    public void issueBook(Long bookId, Long memberId, LocalDate dueDate) {
        LocalDate issueDate = LocalDate.now();
        bookTransactionRepository.save(new BookTransaction(bookId, memberId, issueDate, dueDate));
        Book b = getBookById(bookId);
        b.setBookStatus(Book.BookStatus.ISSUED);
        bookRepository.save(b);
        requestedBooks.remove(bookId);
    }

    public void returnBookToLib(BookTransaction transaction) {
        Book book = transaction.getBook();
        book.setBookStatus(Book.BookStatus.AVAILABLE);
        bookRepository.save(book);
        bookTransactionRepository.delete(transaction);
    }

    public List<Book> findByAuthor(Author a) {
        List<Book> books = new ArrayList<>();
        getAllBooks().forEach(book -> {
            book.getAuthorList()
                .forEach(author -> {
                    if (author.getId()
                              .equals(a.getId()))
                        books.add(book);
                });
        });
        return books;
    }

    public Book findByTitle(String name) {
        return bookRepository.findByTitle(name);
    }

    public Map<Long, Long> getRequestedBooks() {
        return requestedBooks;
    }
}
