package chiamaka.ezeirunne.librarymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;

    private String email;

    @JsonProperty("message")
    private String message;

    @JsonProperty("errors")
    private List<String> errors = new ArrayList<>();

    public boolean isEmptyErrorList(){
        return this.getErrors().isEmpty();
    }
}
