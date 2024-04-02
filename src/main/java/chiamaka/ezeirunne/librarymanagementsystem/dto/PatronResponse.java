package chiamaka.ezeirunne.librarymanagementsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatronResponse {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
}
