package chiamaka.ezeirunne.librarymanagementsystem.data.model;

import jakarta.persistence.*;
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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private int quantityOfBooksAvailable;

    private String isbn;

    private String datePublished;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDateTime registeredDate;

    private LocalDateTime modifiedDate;

}

