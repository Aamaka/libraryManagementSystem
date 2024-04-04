package chiamaka.ezeirunne.librarymanagementsystem.service;

import chiamaka.ezeirunne.librarymanagementsystem.dto.PatronRequest;
import chiamaka.ezeirunne.librarymanagementsystem.dto.PatronResponse;
import chiamaka.ezeirunne.librarymanagementsystem.exception.PatronServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PatronServiceTest {

    @Autowired
    private PatronService patronService;

    @Test
    public void testToRegisterPatron() {
        PatronRequest patronRequest = PatronRequest.builder()
                .firstName("Julius")
                .lastName("Rea")
                .email("julius@gmail.com")
                .phoneNumber("09099433223")
                .gender("MALE")
                .build();

        assertDoesNotThrow(() -> patronService.registerPatron(patronRequest));

        assertThrows(PatronServiceException.class, () -> patronService.registerPatron(patronRequest));

    }

    @Test
    public void testToUpdatePatron() {
        PatronRequest patronRequest = PatronRequest.builder()
                .lastName("Julius")
                .build();

        assertDoesNotThrow(() -> patronService.updatePatron(1L,patronRequest));

    }

    @Test
    public void testToGetPatronById() throws PatronServiceException {
        PatronResponse response = patronService.getPatronById(1L);
        assertEquals("Julius", response.getLastName());
    }


    @Test
    public void testToGetAllPatron(){
        List<PatronResponse> responses = patronService.getAllPatrons();
        assertEquals(1, responses.size());
    }

    @Test
    public void testToDeletePatronById() {
        assertDoesNotThrow(() -> patronService.deletePatronById(1L));
        assertThrows(PatronServiceException.class, () -> patronService.deletePatronById(1L));

    }
}
