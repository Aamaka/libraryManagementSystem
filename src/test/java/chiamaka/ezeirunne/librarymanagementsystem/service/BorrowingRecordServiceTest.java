package chiamaka.ezeirunne.librarymanagementsystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class BorrowingRecordServiceTest {
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @Test
    public void testThatAPatronCanBorrowABook() {

        assertDoesNotThrow(() -> borrowingRecordService.borrowBook(1L, 1L));
    }


    @Test
    public void testThatPatronCanReturnABook(){

        assertDoesNotThrow(() -> borrowingRecordService.returnBook(1L, 1L));
    }
}
