package chiamaka.ezeirunne.librarymanagementsystem.data.repository;

import chiamaka.ezeirunne.librarymanagementsystem.data.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatronRepository extends JpaRepository <Patron, Long>{

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}

