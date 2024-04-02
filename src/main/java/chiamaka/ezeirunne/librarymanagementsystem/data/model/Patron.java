package chiamaka.ezeirunne.librarymanagementsystem.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true, length = 11, nullable = false)
    private String phoneNumber;

    private String address;

    private LocalDateTime registeredDate;

    private LocalDateTime modifiedDate;
}
