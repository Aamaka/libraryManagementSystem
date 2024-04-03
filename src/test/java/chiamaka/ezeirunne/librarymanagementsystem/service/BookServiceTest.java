package chiamaka.ezeirunne.librarymanagementsystem.service;

import chiamaka.ezeirunne.librarymanagementsystem.dto.BookRequest;
import chiamaka.ezeirunne.librarymanagementsystem.dto.BookResponse;
import chiamaka.ezeirunne.librarymanagementsystem.exception.BookServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testToRegisterBook() {
        BookRequest bookRequest = BookRequest.builder()
                .title("Phion")
                .author("Julius Rea")
                .quantityOfBooksAvailable(5)
                .isbn("1267999089033")
                .category("FICTION")
                .datePublished("2023-01-01")
                .build();

        assertDoesNotThrow(() -> bookService.registerBook(bookRequest));

        assertThrows(BookServiceException.class, () -> bookService.registerBook(bookRequest));

    }

    @Test
    public void testToUpdateBook() {
        BookRequest bookRequest = BookRequest.builder()
                .quantityOfBooksAvailable(5)
                .build();

        assertDoesNotThrow(() -> bookService.updateBook(2L,bookRequest));

    }

    @Test
    public void testToGetBookById() throws BookServiceException {
        BookResponse response = bookService.getBookById(2L);
        assertEquals("John ode",response.getAuthor());
    }


    @Test
    public void testToGetAllBooks(){
        List<BookResponse> responses = bookService.getAllBooks();
        assertEquals(6, responses.size());
    }

    @Test
    public void testToDeleteBookById() {
        assertDoesNotThrow(() -> bookService.deleteBookById(2L));
        assertThrows(BookServiceException.class, () -> bookService.deleteBookById(2L));

    }
}

