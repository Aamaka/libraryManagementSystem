package chiamaka.ezeirunne.librarymanagementsystem.service;

import chiamaka.ezeirunne.librarymanagementsystem.dto.UserRequest;
import chiamaka.ezeirunne.librarymanagementsystem.dto.UserResponse;
import chiamaka.ezeirunne.librarymanagementsystem.exception.PatronServiceException;
import chiamaka.ezeirunne.librarymanagementsystem.exception.UserServiceException;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest) throws UserServiceException;

    UserResponse getUserByEmailCredential(String email) throws PatronServiceException;

    UserResponse getUserByIdCredential(Long id);

    UserResponse updateUserPassword(UserRequest updateRequest);

    void deleteUser(Long id);
}
