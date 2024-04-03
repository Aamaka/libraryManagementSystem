package chiamaka.ezeirunne.librarymanagementsystem.controller;

import chiamaka.ezeirunne.librarymanagementsystem.dto.BookResponse;
import chiamaka.ezeirunne.librarymanagementsystem.dto.BookRequest;
import chiamaka.ezeirunne.librarymanagementsystem.exception.BookServiceException;
import chiamaka.ezeirunne.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping
    public ResponseEntity<?> registerBook(@RequestBody BookRequest bookRequest) throws BookServiceException {
        bookService.registerBook(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) throws BookServiceException {
         bookService.updateBook(id, bookRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> bookResponses = bookService.getAllBooks();
        return new ResponseEntity<>(bookResponses, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) throws BookServiceException {
        BookResponse book = bookService.getBookById(id);
        return ResponseEntity.ok().body(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) throws BookServiceException {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }
}
