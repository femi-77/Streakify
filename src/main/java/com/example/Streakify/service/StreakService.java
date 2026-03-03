package com.example.Streakify.service;

import com.example.Streakify.dto.StreakResponseDTO;
import com.example.Streakify.exception.ResourceNotFoundException;
import com.example.Streakify.model.Habit;
import com.example.Streakify.model.HabitLog;
import com.example.Streakify.repository.HabitLogRepository;
import com.example.Streakify.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StreakService {

    private final HabitLogRepository habitLogRepository;
    private final HabitRepository habitRepository;

    public StreakService(HabitLogRepository habitLogRepository,
                         HabitRepository habitRepository) {
        this.habitLogRepository = habitLogRepository;
        this.habitRepository = habitRepository;
    }

    public StreakResponseDTO getStreak(Long habitId) {

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit not found"));

        List<HabitLog> logs =
                habitLogRepository.findByHabitIdOrderByLogDateAsc(habitId);

        int current = calculateCurrentStreak(logs);
        int longest = calculateLongestStreak(logs);

        return new StreakResponseDTO(current, longest);
    }

    private int calculateCurrentStreak(List<HabitLog> logs) {

        if (logs.isEmpty()) return 0;

        int streak = 0;
        LocalDate today = LocalDate.now();
        int lastIndex = logs.size() - 1;

        HabitLog lastLog = logs.get(lastIndex);

        if (!lastLog.getLogDate().equals(today) || !lastLog.getCompleted()) {
            today = today.minusDays(1);
        }

        for (int i = logs.size() - 1; i >= 0; i--) {

            HabitLog log = logs.get(i);

            if (!log.getLogDate().equals(today.minusDays(streak)))
                break;

            if (log.getCompleted())
                streak++;
            else
                break;
        }

        return streak;
    }

    private int calculateLongestStreak(List<HabitLog> logs) {

        if (logs.isEmpty()) return 0;

        int longest = 0;
        int current = 0;

        LocalDate previousDate = null;

        for (HabitLog log : logs) {

            if (!log.getCompleted()) {
                current = 0;
                previousDate = null;
                continue;
            }

            if (previousDate == null) {
                current = 1;
            } else if (log.getLogDate().equals(previousDate.plusDays(1))) {
                current++;
            } else {
                current = 1; // date gap → restart streak
            }

            longest = Math.max(longest, current);
            previousDate = log.getLogDate();
        }

        return longest;
    }
}