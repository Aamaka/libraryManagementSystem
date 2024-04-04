package chiamaka.ezeirunne.librarymanagementsystem.controller;


import chiamaka.ezeirunne.librarymanagementsystem.dto.UserRequest;
import chiamaka.ezeirunne.librarymanagementsystem.dto.UserResponse;
import chiamaka.ezeirunne.librarymanagementsystem.exception.PatronServiceException;
import chiamaka.ezeirunne.librarymanagementsystem.exception.UserServiceException;
import chiamaka.ezeirunne.librarymanagementsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest) throws UserServiceException {
        return ResponseEntity.ok(userService.registerUser(userRequest));
    }


    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> getUserByEmailCredential(@PathVariable String email) throws PatronServiceException {
        return ResponseEntity.ok(userService.getUserByEmailCredential(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserByIdCredential(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserByIdCredential(id));
    }

    @PutMapping
    public ResponseEntity<UserResponse> editUserDetail(@Valid @RequestBody UserRequest updateRequest) {
        return ResponseEntity.ok(userService.updateUserPassword(updateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}

