package chiamaka.ezeirunne.librarymanagementsystem.service;

import chiamaka.ezeirunne.librarymanagementsystem.dto.PatronRequest;
import chiamaka.ezeirunne.librarymanagementsystem.dto.PatronResponse;
import chiamaka.ezeirunne.librarymanagementsystem.exception.PatronServiceException;

import java.util.List;

public interface PatronService {

    PatronResponse registerPatron(PatronRequest patronRequest) throws PatronServiceException;
    PatronResponse updatePatron(Long id, PatronRequest patronRequest) throws PatronServiceException;
    List<PatronResponse> getAllPatrons();
    PatronResponse getPatronById(Long id) throws PatronServiceException;
    void deletePatronById(Long id) throws PatronServiceException;


}
