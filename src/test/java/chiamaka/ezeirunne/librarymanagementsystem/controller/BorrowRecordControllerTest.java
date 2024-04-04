package chiamaka.ezeirunne.librarymanagementsystem.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BorrowRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testBorrowBookEndpoint() throws Exception {

        mockMvc.perform(post("/api/borrow/7/patron/4"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testReturnBookEndpoint() throws Exception {

        mockMvc.perform(put("/api/return/7/patron/4"))
                .andExpect(status().isOk());

    }
}
