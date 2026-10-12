package com.example.erp.service;

import com.example.erp.annotation.Auditable;
import com.example.erp.dto.UserResponseDTO;
import com.example.erp.entity.User;
import com.example.erp.exception.ResourceNotFoundException;
import com.example.erp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Auditable(action = "CREATE", entity = "USER")
    public User createUser(User user) {
        validateNewUser(user);
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Auditable(action = "UPDATE", entity = "USER")
    public User updateUser(User user) {

        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        validateUserUpdate(user, existingUser);

        // Update only fields provided in the request
        if (user.getUsername() != null && !user.getUsername().isBlank()) {
            existingUser.setUsername(user.getUsername());
        }

        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        if (user.getRole() != null && !user.getRole().isBlank()) {
            existingUser.setRole(user.getRole());
        }

        // Preserve the existing password if no new password is provided
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            existingUser.setPassword(
                    passwordEncoder.encode(user.getPassword()));
        }

        // Allow employee association to be changed when supplied
        if (user.getEmployee() != null) {
            existingUser.setEmployee(user.getEmployee());
        }

        return userRepository.save(existingUser);
    }

    @Auditable(action = "DELETE", entity = "USER")
    public User deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);

        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getEmployee() != null
                        ? user.getEmployee().getId()
                        : null);
    }

    private void validateNewUser(User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    private void validateUserUpdate(User user, User existingUser) {

        if (user.getUsername() != null
                && !user.getUsername().isBlank()
                && !user.getUsername().equals(existingUser.getUsername())
                && userRepository.findByUsername(user.getUsername()).isPresent()) {

            throw new IllegalArgumentException("Username already exists");
        }

        if (user.getEmail() != null
                && !user.getEmail().equals(existingUser.getEmail())
                && userRepository.findByEmail(user.getEmail()).isPresent()) {

            throw new IllegalArgumentException("Email already exists");
        }
    }
}