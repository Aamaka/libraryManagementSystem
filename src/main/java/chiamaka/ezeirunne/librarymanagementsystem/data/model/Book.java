package chiamaka.ezeirunne.librarymanagementsystem.data.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Data
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    private BigDecimal price;
    private String author;
    private int quantityOfBooksAvailable;
    private String isbn;
    private String datePublished;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String createdDate;
    private String modifiedDate;

}

