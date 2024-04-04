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

import java.util.List;
import java.util.Optional;

@Service
public class PatronServiceImpl implements PatronService {

    @Autowired
    private PatronRepository patronRepository;

    @Override
    @Transactional
    public PatronResponse registerPatron(PatronRequest patronRequestDto) {
        Optional<PatronResponse> response = validatePatronRequest(patronRequestDto);
        return response.orElseGet(() -> getPatronResponse(patronRepository.save(Patron.builder()
                .firstName(patronRequestDto.getFirstName())
                .lastName(patronRequestDto.getLastName())
                .email(patronRequestDto.getEmail())
                .phoneNumber(patronRequestDto.getPhoneNumber())
                .gender(Gender.valueOf(patronRequestDto.getGender()))
                .build())));

    }

    private Optional<PatronResponse> validatePatronRequest(PatronRequest patronRequest) {
        String email = patronRequest.getEmail();
        String phoneNumber = patronRequest.getPhoneNumber();

        if (patronRepository.existsByEmail(email)) {
            return Optional.of(PatronResponse.builder()
                    .message("Patron with email " + email + " already exists")
                    .build());
        }

        if (patronRepository.existsByPhoneNumber(phoneNumber)) {
            return Optional.of(PatronResponse.builder()
                    .message("Patron with phone number " + phoneNumber + " already exists")
                    .build());
        }

        return Optional.empty();
    }


    @Override
    @Transactional
    public PatronResponse updatePatron(Long id, PatronRequest patronRequest) throws PatronServiceException {
        Patron existingPatron = getPatron(id);

        if(patronRequest.getFirstName() != null){
            existingPatron.setFirstName(patronRequest.getFirstName());
        }

        if(patronRequest.getLastName() != null){
            existingPatron.setLastName(patronRequest.getLastName());
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

        patronRepository.save(existingPatron);

        return getPatronResponse(existingPatron);
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
                .firstName(patron.getFirstName())
                .lastName(patron.getLastName())
                .email(patron.getEmail())
                .gender(String.valueOf(patron.getGender()))
                .phoneNumber(patron.getPhoneNumber())
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
    public void deletePatronById(Long id) throws PatronServiceException {
        patronRepository.delete(getPatron(id));
    }

}
