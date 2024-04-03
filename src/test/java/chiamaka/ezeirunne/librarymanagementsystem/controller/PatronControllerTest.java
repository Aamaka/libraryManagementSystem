package chiamaka.ezeirunne.librarymanagementsystem.controller;

import chiamaka.ezeirunne.librarymanagementsystem.dto.PatronRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatronControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void initSetUp(){
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testRegisterPatronEndpoint() throws Exception {

        PatronRequest dto = PatronRequest.
                builder()
                .name("Jane Amber")
                .email("janeamber@gmail.com")
                .phoneNumber("09087784332")
                .gender("FEMALE")
                . build();


        mockMvc.perform(post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateExistingPatronEndpoint() throws Exception {

        PatronRequest dto = PatronRequest.
                builder()
                .name("Dim Amber")
                .build();

        mockMvc.perform(put("/api/patrons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetPatronByIdEndpoint() throws Exception {
        mockMvc.perform(get("/api/patrons/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllPatronsEndpoint() throws Exception {
        mockMvc.perform(get("/api/patrons"))
                .andExpect(status().isOk());
    }


    @Test
    public void testDeletePatronByIdEndpoint() throws Exception {
        mockMvc.perform(delete("/api/patrons/1"))
                .andExpect(status().isOk());
    }

}
