package kz.home.librarysystem.repository;

import kz.home.librarysystem.model.Author;
import kz.home.librarysystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByBookStatus(Book.BookStatus status);

    Book findByTitle(String title);

}
