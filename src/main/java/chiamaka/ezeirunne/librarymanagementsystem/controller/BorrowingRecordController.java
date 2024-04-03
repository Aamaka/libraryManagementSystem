package chiamaka.ezeirunne.librarymanagementsystem.controller;

import chiamaka.ezeirunne.librarymanagementsystem.exception.BookServiceException;
import chiamaka.ezeirunne.librarymanagementsystem.exception.BorrowingRecordServiceException;
import chiamaka.ezeirunne.librarymanagementsystem.exception.PatronServiceException;
import chiamaka.ezeirunne.librarymanagementsystem.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<?> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) throws PatronServiceException, BookServiceException, BorrowingRecordServiceException {
        borrowingRecordService.borrowBook(bookId, patronId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) throws PatronServiceException, BookServiceException, BorrowingRecordServiceException {
        borrowingRecordService.returnBook(bookId, patronId);
        return ResponseEntity.ok().build();
    }
}
