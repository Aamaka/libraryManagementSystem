package chiamaka.ezeirunne.librarymanagementsystem.security;

import chiamaka.ezeirunne.librarymanagementsystem.security.auth.model.AuthenticationRequest;
import chiamaka.ezeirunne.librarymanagementsystem.security.auth.model.AuthenticationResponse;
import chiamaka.ezeirunne.librarymanagementsystem.security.auth.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
