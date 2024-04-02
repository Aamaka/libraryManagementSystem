package chiamaka.ezeirunne.librarymanagementsystem.data.repository;

import chiamaka.ezeirunne.librarymanagementsystem.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitleAndAuthor(String title, String author);

    boolean existsByTitleIsIgnoreCaseAndAuthorIgnoreCase(String title, String author);

}
