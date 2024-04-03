package chiamaka.ezeirunne.librarymanagementsystem.controller;

import chiamaka.ezeirunne.librarymanagementsystem.dto.BookRequest;
import chiamaka.ezeirunne.librarymanagementsystem.exception.BookServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void initSetUp(){
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testRegisterBookEndpoint() throws Exception {

        BookRequest dto = BookRequest.
                builder()
                .title("Love")
                .author("Peace Checkers")
                .isbn("074324626809")
                .category("FICTION")
                .quantityOfBooksAvailable(10)
                .datePublished("13th july 2023")
                . build();


        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

    }

    @Test
    public void testUpdateExistingBookEndpoint() throws Exception {

        BookRequest dto = BookRequest.
                builder()
                .title("fame")
                .author("Peace Amber")
                .datePublished("13th August 2023")
                . build();

        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetBookByIdEndpoint() throws Exception {
        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllBooksEndpoint() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk());
    }


    @Test
    public void testDeleteBookByIdEndpoint() throws Exception {
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk());
    }


}