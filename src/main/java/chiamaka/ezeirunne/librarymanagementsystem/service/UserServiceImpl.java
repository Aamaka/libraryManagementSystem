package chiamaka.ezeirunne.librarymanagementsystem.service;

import chiamaka.ezeirunne.librarymanagementsystem.config.ApplicationConfig;
import chiamaka.ezeirunne.librarymanagementsystem.data.model.Role;
import chiamaka.ezeirunne.librarymanagementsystem.data.model.User;
import chiamaka.ezeirunne.librarymanagementsystem.data.repository.UserRepository;
import chiamaka.ezeirunne.librarymanagementsystem.dto.UserRequest;
import chiamaka.ezeirunne.librarymanagementsystem.dto.UserResponse;
import chiamaka.ezeirunne.librarymanagementsystem.exception.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Override
    public UserResponse registerUser(UserRequest userRequest) throws UserServiceException {
        validateUserRequest(userRequest);
         return getUserResponse(userRepository.save(User.builder()
                 .email(userRequest.getEmail())
                 .password(applicationConfig.passwordEncoder().encode(userRequest.getPassword()))
                 .isEnabled(true)
                 .role(Role.ADMIN)
                 .build()));

    }

    private void validateUserRequest(UserRequest userRequest) throws UserServiceException {
        String email = userRequest.getEmail();

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new UserServiceException("User with email " + email + " already exists");
        }
    }

    private UserResponse getUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .errors(new ArrayList<>())
                .build();
    }


    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User Not Found" ));
    }

    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User Not Found" ));
    }

    @Override
    public UserResponse getUserByEmailCredential(String email) {
       return getUserResponse(getUserByEmail(email));
    }

    @Override
    public UserResponse getUserByIdCredential(Long id) {
         return getUserResponse(getUserById(id));
    }

    @Override
    public UserResponse updateUserPassword(UserRequest userRequest) {
        return getUserResponse(userRepository.save(User.builder()
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build()));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(getUserById(id));
    }


}
