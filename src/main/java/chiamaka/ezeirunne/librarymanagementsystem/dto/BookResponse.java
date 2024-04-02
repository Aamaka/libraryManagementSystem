package chiamaka.ezeirunne.librarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private int quantityOfBooksAvailable;
    private String isbn;
    private String datePublished;
    private String category;
}
