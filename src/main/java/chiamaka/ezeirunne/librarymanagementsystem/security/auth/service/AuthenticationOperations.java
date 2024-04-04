package chiamaka.ezeirunne.librarymanagementsystem.security.auth.service;

import chiamaka.ezeirunne.librarymanagementsystem.security.auth.model.AuthenticationRequest;
import chiamaka.ezeirunne.librarymanagementsystem.security.auth.model.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationOperations {

    private final AuthenticationComponent authenticationComponent;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        return authenticationComponent.authenticate(authenticationRequest);
    }
}
