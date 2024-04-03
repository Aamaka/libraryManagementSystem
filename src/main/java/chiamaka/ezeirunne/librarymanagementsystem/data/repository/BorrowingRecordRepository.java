package chiamaka.ezeirunne.librarymanagementsystem.data.repository;

import chiamaka.ezeirunne.librarymanagementsystem.data.model.Book;
import chiamaka.ezeirunne.librarymanagementsystem.data.model.BorrowingRecord;
import chiamaka.ezeirunne.librarymanagementsystem.data.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    Optional<BorrowingRecord> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);

    boolean existsByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);


}
