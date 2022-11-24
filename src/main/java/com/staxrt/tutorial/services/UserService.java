package com.staxrt.tutorial.services;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.User;
import com.staxrt.tutorial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) throws Exception {
        if (isEmailExists(user.getEmail())) {
            throw new ResourceNotFoundException("This email is already in database ");
        } else {
            userRepository.save(user);
            return user;
        }
    }

    public User deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
        return user.orElse(null);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User userDetails) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setFirstName(userDetails.getFirstName());
            user.get().setEmail(userDetails.getEmail());
            user.get().setLastName(userDetails.getLastName());
            userRepository.save(user.get());
            return user.get();
        } else {
            throw new ResourceNotFoundException("Unable to find user: " + id);
        }
    }

    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
