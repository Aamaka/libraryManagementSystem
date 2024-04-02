package chiamaka.ezeirunne.librarymanagementsystem.exception;

import org.hibernate.service.spi.ServiceException;

public class PatronServiceException extends Exception {
    public PatronServiceException(String message) {
        super(message);
    }
}
