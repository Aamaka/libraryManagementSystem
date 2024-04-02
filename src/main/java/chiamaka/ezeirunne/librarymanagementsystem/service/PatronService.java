package chiamaka.ezeirunne.librarymanagementsystem.service;

import chiamaka.ezeirunne.librarymanagementsystem.dto.PatronRequest;
import chiamaka.ezeirunne.librarymanagementsystem.dto.PatronResponse;
import chiamaka.ezeirunne.librarymanagementsystem.exception.PatronServiceException;

import java.util.List;

public interface PatronService {

    void registerPatron(PatronRequest patronRequest) throws PatronServiceException;
    void updatePatron(Long id, PatronRequest patronRequest) throws PatronServiceException;
    List<PatronResponse> getAllPatrons();
    PatronResponse getPatronById(Long id) throws PatronServiceException;
    void deletePatron(Long id) throws PatronServiceException;


}
