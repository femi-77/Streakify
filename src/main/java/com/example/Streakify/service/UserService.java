package com.example.Streakify.service;

import com.example.Streakify.dto.UserRequestDTO;
import com.example.Streakify.dto.UserResponseDTO;
import com.example.Streakify.exception.EmailAlreadyExistsException;
import com.example.Streakify.exception.ResourceNotFoundException;
import com.example.Streakify.model.User;
import com.example.Streakify.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public UserResponseDTO createUser(UserRequestDTO dto){
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setCreatedAt(LocalDateTime.now());

        User saved = userRepository.save(user);
        return convertToDTO(saved);
    }
    public UserResponseDTO getUserById(Long id){
        User user=userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id "+id));
        return convertToDTO(user);
    }


    public void deleteUser(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + id));

        userRepository.delete(user);
    }

    private UserResponseDTO convertToDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
