package chiamaka.ezeirunne.librarymanagementsystem.security.auth.service;

import chiamaka.ezeirunne.librarymanagementsystem.security.auth.model.AuthenticationRequest;
import chiamaka.ezeirunne.librarymanagementsystem.security.auth.model.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationOperations authenticationOperations;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        return authenticationOperations.authenticate(authenticationRequest);
    }
}
