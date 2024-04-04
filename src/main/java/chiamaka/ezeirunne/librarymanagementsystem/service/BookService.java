package chiamaka.ezeirunne.librarymanagementsystem.service;

import chiamaka.ezeirunne.librarymanagementsystem.dto.BookRequest;
import chiamaka.ezeirunne.librarymanagementsystem.dto.BookResponse;
import chiamaka.ezeirunne.librarymanagementsystem.exception.BookServiceException;

import java.util.List;

public interface BookService {

    BookResponse registerBook(BookRequest bookRequest) throws BookServiceException;

    BookResponse updateBook(Long id, BookRequest bookRequest) throws BookServiceException;

    List<BookResponse> getAllBooks();

    BookResponse getBookById(Long id) throws BookServiceException;

    void deleteBookById(Long id) throws BookServiceException;
}
