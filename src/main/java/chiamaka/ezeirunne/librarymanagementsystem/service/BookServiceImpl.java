package chiamaka.ezeirunne.librarymanagementsystem.service;

import chiamaka.ezeirunne.librarymanagementsystem.data.model.Book;
import chiamaka.ezeirunne.librarymanagementsystem.data.model.Category;
import chiamaka.ezeirunne.librarymanagementsystem.data.repository.BookRepository;
import chiamaka.ezeirunne.librarymanagementsystem.dto.BookRequest;
import chiamaka.ezeirunne.librarymanagementsystem.dto.BookResponse;
import chiamaka.ezeirunne.librarymanagementsystem.exception.BookServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookServiceImpl implements  BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void registerBook(BookRequest bookRequest) throws BookServiceException {

        validateBookRequest(bookRequest);

        bookRepository.save(Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .quantityOfBooksAvailable(Math.max(bookRequest.getQuantityOfBooksAvailable(), 0))
                .isbn(bookRequest.getIsbn())
                .datePublished(bookRequest.getDatePublished())
                .category(Category.valueOf(bookRequest.getCategory()))
                .registeredDate(LocalDateTime.now())
                .build());

    }

    private void validateBookRequest(BookRequest bookRequest) throws BookServiceException {
        if (bookRepository.existsByTitleIsIgnoreCaseAndAuthorIgnoreCase(bookRequest.getTitle(), bookRequest.getAuthor())) {
            throw new BookServiceException("Book with title " + bookRequest.getTitle() + "and author "+ bookRequest.getAuthor() + " already exists");
        }
    }

    @Override
    @Transactional
    public void updateBook(Long id, BookRequest bookRequest) throws BookServiceException {
        Book existingBook = getBook(id);

        if (bookRequest.getTitle() != null && bookRequest.getAuthor() != null){
            validateBookRequest(bookRequest);
        }

        if(bookRequest.getAuthor() != null){
            existingBook.setAuthor(bookRequest.getAuthor());
        }

        if(bookRequest.getTitle() != null){
            existingBook.setTitle(bookRequest.getTitle());
        }

        if(bookRequest.getQuantityOfBooksAvailable() > 0){
            existingBook.setQuantityOfBooksAvailable(bookRequest.getQuantityOfBooksAvailable());
        }

        if(bookRequest.getCategory() != null){
            existingBook.setCategory(Category.valueOf(bookRequest.getCategory()));
        }

        if (bookRequest.getIsbn() != null){
            existingBook.setIsbn(bookRequest.getIsbn());
        }

        if (bookRequest.getDatePublished() != null){
            existingBook.setDatePublished(bookRequest.getDatePublished());
        }

        existingBook.setModifiedDate(LocalDateTime.now());
        bookRepository.save(existingBook);

    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::getBookResponse).toList();
    }

    private BookResponse getBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .quantityOfBooksAvailable(book.getQuantityOfBooksAvailable())
                .isbn(book.getIsbn())
                .datePublished(book.getDatePublished())
                .category(String.valueOf(book.getCategory()))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse getBookById(Long id) throws BookServiceException {
        return getBookResponse(getBook(id));
    }

    private Book getBook(Long id) throws BookServiceException {
        return bookRepository.findById(id).orElseThrow(()-> new BookServiceException("Book with id "+ id + " does not exist" ));
    }

    @Override
    @Transactional
    public void deleteBook(Long id) throws BookServiceException {
        bookRepository.delete(getBook(id));
    }

}
