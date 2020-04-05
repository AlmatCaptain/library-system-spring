package kz.home.librarysystem.repository;

import kz.home.librarysystem.model.BookTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTransactionRepository  extends JpaRepository<BookTransaction,Long> {
}
