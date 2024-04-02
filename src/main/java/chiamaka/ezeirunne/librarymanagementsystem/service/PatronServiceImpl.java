package chiamaka.ezeirunne.librarymanagementsystem.service;

import chiamaka.ezeirunne.librarymanagementsystem.data.model.Gender;
import chiamaka.ezeirunne.librarymanagementsystem.data.model.Patron;
import chiamaka.ezeirunne.librarymanagementsystem.data.repository.PatronRepository;
import chiamaka.ezeirunne.librarymanagementsystem.dto.PatronRequest;
import chiamaka.ezeirunne.librarymanagementsystem.dto.PatronResponse;
import chiamaka.ezeirunne.librarymanagementsystem.exception.PatronServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatronServiceImpl implements PatronService {

    @Autowired
    private PatronRepository patronRepository;

    @Override
    @Transactional
    public void registerPatron(PatronRequest patronRequestDto) throws PatronServiceException {
        validatePatronRequest(patronRequestDto);

        patronRepository.save(Patron.builder()
                .name(patronRequestDto.getName())
                .email(patronRequestDto.getEmail())
                .phoneNumber(patronRequestDto.getPhoneNumber())
                .gender(Gender.valueOf(patronRequestDto.getGender()))
                .registeredDate(LocalDateTime.now())
                .build());
    }

    @Override
    @Transactional
    public void updatePatron(Long id, PatronRequest patronRequest) throws PatronServiceException {
        Patron existingPatron = getPatron(id);

        if(patronRequest.getName() != null){
            existingPatron.setName(patronRequest.getName());
        }

        if(patronRequest.getEmail() != null && !patronRepository.existsByEmail(patronRequest.getEmail())){
            existingPatron.setEmail(patronRequest.getEmail());
        }

        if(patronRequest.getPhoneNumber() != null && !patronRepository.existsByPhoneNumber(patronRequest.getPhoneNumber())){
            existingPatron.setPhoneNumber(patronRequest.getPhoneNumber());
        }

        if(patronRequest.getGender() != null){
            existingPatron.setGender(Gender.valueOf(patronRequest.getGender()));
        }

        existingPatron.setModifiedDate(LocalDateTime.now());

        patronRepository.save(existingPatron);
    }

    private void validatePatronRequest(PatronRequest patronRequest) throws PatronServiceException {
        String email = patronRequest.getEmail();
        String phoneNumber = patronRequest.getPhoneNumber();

        if (patronRepository.existsByEmail(patronRequest.getEmail())) {
            throw new PatronServiceException("Patron with email " + email + " already exists");
        }

        if (patronRepository.existsByPhoneNumber(patronRequest.getPhoneNumber())) {
            throw new PatronServiceException("Patron with phone number " + phoneNumber + " already exists");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatronResponse> getAllPatrons() {

        List<Patron> patrons = patronRepository.findAll();
        return patrons.stream().map(this::getPatronResponse).toList();

    }

    private PatronResponse getPatronResponse(Patron patron) {
        return PatronResponse.builder()
                .id(patron.getId())
                .name(patron.getName())
                .email(patron.getEmail())
                .gender(String.valueOf(patron.getGender()))
                .build();
    }


    @Override
    @Transactional(readOnly = true)
    public PatronResponse getPatronById(Long id) throws PatronServiceException {
        return getPatronResponse(getPatron(id));
    }

    private Patron getPatron(Long id) throws PatronServiceException {
        return patronRepository.findById(id).orElseThrow(()-> new PatronServiceException("Patron with id "+ id + " does not exist" ));
    }

    @Override
    @Transactional
    public void deletePatron(Long id) throws PatronServiceException {
        patronRepository.delete(getPatron(id));
    }

}
