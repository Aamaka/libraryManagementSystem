package chiamaka.ezeirunne.librarymanagementsystem.security.auth.service;

import chiamaka.ezeirunne.librarymanagementsystem.data.repository.UserRepository;
import chiamaka.ezeirunne.librarymanagementsystem.security.auth.model.AuthenticationRequest;
import chiamaka.ezeirunne.librarymanagementsystem.security.auth.model.AuthenticationResponse;
import chiamaka.ezeirunne.librarymanagementsystem.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationComponent {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.getToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}

