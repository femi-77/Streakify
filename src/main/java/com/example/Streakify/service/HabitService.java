package com.example.Streakify.service;

import com.example.Streakify.dto.HabitRequestDTO;
import com.example.Streakify.dto.HabitResponseDTO;
import com.example.Streakify.exception.DuplicateResourceException;
import com.example.Streakify.model.Habit;
import com.example.Streakify.model.User;
import com.example.Streakify.repository.HabitRepository;
import com.example.Streakify.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HabitService {
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitService(HabitRepository habitRepository,UserRepository userRepository){
        this.habitRepository=habitRepository;
        this.userRepository=userRepository;
    }
    public HabitResponseDTO createHabit(HabitRequestDTO dto){

        if (habitRepository.existsByUserIdAndName(dto.getUserId(), dto.getName())) {
            throw new DuplicateResourceException("Habit already exists");
        }
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Habit habit = new Habit();
        habit.setName(dto.getName());
        habit.setTargetDaysPerWeek(dto.getTargetDaysPerWeek());
        habit.setUser(user);
        habit.setCreatedAt(LocalDateTime.now());
        Habit saved = habitRepository.save(habit);
        return convertToDTO(saved);
    }

    public List<HabitResponseDTO> getHabitByUser(Long userId){
        List<Habit> habits= habitRepository.findByUserId(userId);
        return habits.stream()
                .map(this::convertToDTO)
                .toList();
    }
    public void deleteHabit(Long habitId){
        userRepository.deleteById(habitId);
    }

    private HabitResponseDTO convertToDTO(Habit habit){
        return new HabitResponseDTO(
                habit.getId(),
                habit.getName(),
                habit.getTargetDaysPerWeek()
        );
    }

}
