package chiamaka.ezeirunne.librarymanagementsystem.service;

import chiamaka.ezeirunne.librarymanagementsystem.data.model.Book;
import chiamaka.ezeirunne.librarymanagementsystem.data.model.BorrowingRecord;
import chiamaka.ezeirunne.librarymanagementsystem.data.model.Patron;
import chiamaka.ezeirunne.librarymanagementsystem.data.repository.BookRepository;
import chiamaka.ezeirunne.librarymanagementsystem.data.repository.BorrowingRecordRepository;
import chiamaka.ezeirunne.librarymanagementsystem.data.repository.PatronRepository;
import chiamaka.ezeirunne.librarymanagementsystem.exception.BookServiceException;
import chiamaka.ezeirunne.librarymanagementsystem.exception.BorrowingRecordServiceException;
import chiamaka.ezeirunne.librarymanagementsystem.exception.PatronServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Override
    @Transactional
    public void borrowBook(Long bookId, Long patronId) throws BookServiceException, PatronServiceException, BorrowingRecordServiceException {
        Book book = getBook(bookId);

        Patron patron = getPatron(patronId);
        if(borrowingRecordRepository.existsByBookAndPatronAndReturnDateIsNull(book, patron)){
            throw new BorrowingRecordServiceException("You are yet to return : " + book.getTitle());
        }

        if (book.getQuantityOfBooksAvailable() < 1) {
            throw new BookServiceException("Book is not available for borrowing: " + book.getTitle());
        }

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDateTime.now());


        book.setQuantityOfBooksAvailable(book.getQuantityOfBooksAvailable() - 1 );

        bookRepository.save(book);

        borrowingRecordRepository.save(borrowingRecord);
    }

    private Patron getPatron(Long patronId) throws PatronServiceException {
        return patronRepository.findById(patronId)
                .orElseThrow(() -> new PatronServiceException("Patron not found with id: " + patronId));
    }

    private Book getBook(Long bookId) throws BookServiceException {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookServiceException("Book not found with id: " + bookId));
    }


    @Override
    @Transactional
    public void returnBook(Long bookId, Long patronId) throws BookServiceException, PatronServiceException, BorrowingRecordServiceException {
        Book book = getBook(bookId);

        Patron patron = getPatron(patronId);

        BorrowingRecord borrowingRecord = getBorrowingRecord(book, patron);

        borrowingRecord.setReturnDate(LocalDateTime.now());

        book.setQuantityOfBooksAvailable(book.getQuantityOfBooksAvailable() + 1);

        bookRepository.save(book);

        borrowingRecordRepository.save(borrowingRecord);
    }

    private BorrowingRecord getBorrowingRecord(Book book, Patron patron) throws BorrowingRecordServiceException {
        return borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(book, patron)
                .orElseThrow(() -> new BorrowingRecordServiceException("Borrowing record not found"));
    }

}
