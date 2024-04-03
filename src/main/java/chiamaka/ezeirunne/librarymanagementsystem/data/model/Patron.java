package chiamaka.ezeirunne.librarymanagementsystem.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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

    @Column(unique = true)
    @Email(message = "Invalid email format")
    @NotNull(message = "Email cannot be null")
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true, length = 11, nullable = false)
    @NotNull(message = "Phone number cannot be null")
    private String phoneNumber;

    private String address;

    private LocalDateTime registeredDate;

    private LocalDateTime modifiedDate;
}
