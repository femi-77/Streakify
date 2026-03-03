package com.example.Streakify.service;

import com.example.Streakify.dto.HabitLogGetDTO;
import com.example.Streakify.dto.HabitLogResponseDTO;
import com.example.Streakify.exception.BadRequestException;
import com.example.Streakify.exception.DuplicateResourceException;
import com.example.Streakify.exception.ResourceNotFoundException;
import com.example.Streakify.model.Habit;
import com.example.Streakify.model.HabitLog;
import com.example.Streakify.repository.HabitLogRepository;
import com.example.Streakify.repository.HabitRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HabitLogService {
    private final HabitLogRepository habitLogRepository;
    private final HabitRepository habitRepository;
    public  HabitLogService(HabitLogRepository habitLogRepository,HabitRepository habitRepository){
        this.habitLogRepository=habitLogRepository;
        this.habitRepository=habitRepository;

    }

    public HabitLogResponseDTO createLog(Long habitId, LocalDate date,boolean completed){
        if(date.isAfter(LocalDate.now())){
            throw new BadRequestException("Cannot log future dates");
        }


        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit not found with id: " + habitId));


        Optional<HabitLog> existingLog=
                habitLogRepository.findByHabitIdAndLogDate(habitId,date);
        if(existingLog.isPresent()){
            throw new DuplicateResourceException("Log already exists for this date");
        }

        HabitLog log=new HabitLog();
        log.setHabit(habit);
        log.setLogDate(date);
        log.setCompleted(completed);

        HabitLog savedLog=habitLogRepository.save(log);
        return convertToDTO(savedLog);
    }

    public HabitLogResponseDTO updateLog(Long habitId,LocalDate date,boolean completed){
        if(date.isAfter(LocalDate.now())){
            throw new BadRequestException("Cannot log future dates");
        }
        HabitLog log=habitLogRepository
                .findByHabitIdAndLogDate(habitId,date)
                .orElseThrow(()->new ResourceNotFoundException("Log not found"));
        log.setCompleted(completed);

        HabitLog updated=habitLogRepository.save(log);

        return convertToDTO(updated);
    }

    public List<HabitLogGetDTO> getLogsByHabit(Long habitId){
        List<HabitLog> logs=habitLogRepository.findByHabitId(habitId);

        return logs.stream()
                .map(log -> new HabitLogGetDTO(
                log.getId(),
                log.getLogDate(),
                log.getCompleted()
        ))
                .toList();
    }

    private String calculateWeeklyStatus(Habit habit) {

        List<HabitLog> logs =
                habitLogRepository.findByHabitId(habit.getId());

        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(java.time.DayOfWeek.SUNDAY);

        int completedCount = 0;

        for (HabitLog log : logs) {
            if (!log.getLogDate().isBefore(startOfWeek)
                    && !log.getLogDate().isAfter(endOfWeek)
                    && log.getCompleted()) {
                completedCount++;
            }
        }

        int target = habit.getTargetDaysPerWeek();

        if (completedCount < target) {
            return "In Progress";
        } else if (completedCount == target) {
            return "Target Achieved! ";
        } else {
            return "Target Exceeded! Keep Going!";
        }
    }

    private HabitLogResponseDTO convertToDTO(HabitLog log){
       String weeklyStatus=calculateWeeklyStatus(log.getHabit());
        return new HabitLogResponseDTO(
                log.getId(),
                log.getLogDate(),
                log.getCompleted(),
                weeklyStatus
        );
    }
}
