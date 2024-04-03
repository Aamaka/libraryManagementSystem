package chiamaka.ezeirunne.librarymanagementsystem.service;

import chiamaka.ezeirunne.librarymanagementsystem.exception.BookServiceException;
import chiamaka.ezeirunne.librarymanagementsystem.exception.BorrowingRecordServiceException;
import chiamaka.ezeirunne.librarymanagementsystem.exception.PatronServiceException;

public interface BorrowingRecordService {
    void borrowBook(Long bookId, Long patronId) throws BookServiceException, PatronServiceException, BorrowingRecordServiceException;

    void returnBook(Long bookId, Long patronId) throws BookServiceException, PatronServiceException, BorrowingRecordServiceException;
}
