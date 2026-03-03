package com.example.Streakify.service;

import com.example.Streakify.dto.CurrentStreakItemDTO;
import com.example.Streakify.dto.DashboardResponseDTO;
import com.example.Streakify.exception.ResourceNotFoundException;
import com.example.Streakify.model.Habit;
import com.example.Streakify.model.HabitLog;
import com.example.Streakify.repository.HabitLogRepository;
import com.example.Streakify.repository.HabitRepository;
import com.example.Streakify.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    private final HabitRepository habitRepository;
    private final HabitLogRepository habitLogRepository;
    private final StreakService streakService;
    private final UserRepository userRepository;

    public DashboardService(HabitRepository habitRepository,
                            HabitLogRepository habitLogRepository,
                            StreakService streakService,
                            UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.habitLogRepository = habitLogRepository;
        this.streakService = streakService;
        this.userRepository = userRepository;
    }

    public DashboardResponseDTO getDashboard(Long userId) {

        //  Check user exists
        userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        //  Fetch habits
        List<Habit> habits = habitRepository.findByUserId(userId);

        int totalHabits = habits.size();
        int completedToday = 0;

        LocalDate today = LocalDate.now();

        //  Count active habits (any log in last 7 days)
        long activeHabits = habits.stream()
                .filter(habit ->
                        habitLogRepository.existsByHabitIdAndLogDateAfter(
                                habit.getId(),
                                today.minusDays(7)
                        )
                )
                .count();

        //  Streak details
        List<CurrentStreakItemDTO> streakItems = new ArrayList<>();

        for (Habit habit : habits) {

            // Count completed today
            List<HabitLog> logs =
                    habitLogRepository.findByHabitId(habit.getId());

            for (HabitLog log : logs) {
                if (log.getLogDate().equals(today) && log.getCompleted()) {
                    completedToday++;
                }
            }

            var streak = streakService.getStreak(habit.getId());

            streakItems.add(
                    new CurrentStreakItemDTO(
                            habit.getName(),
                            streak.getCurrentStreak(),
                            streak.getLongestStreak()
                    )
            );
        }

        //  Weekly target-based consistency
        int consistencyScore = calculateConsistency(userId);

        return new DashboardResponseDTO(
                totalHabits,
                activeHabits,
                completedToday,
                streakItems,
                consistencyScore
        );
    }

    private int calculateConsistency(Long userId) {

        List<Habit> habits = habitRepository.findByUserId(userId);
        if (habits.isEmpty()) return 0;

        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);

        long daysPassed =
                ChronoUnit.DAYS.between(startOfWeek, today) + 1;

        long completedThisWeek = 0;

        for (Habit habit : habits) {

            List<HabitLog> logs =
                    habitLogRepository.findByHabitId(habit.getId());

            for (HabitLog log : logs) {

                if (log.getCompleted()
                        && !log.getLogDate().isBefore(startOfWeek)
                        && !log.getLogDate().isAfter(today)) {

                    completedThisWeek++;
                }
            }
        }

        int totalWeeklyTarget = habits.stream()
                .mapToInt(Habit::getTargetDaysPerWeek)
                .sum();

        double expectedTillToday =
                totalWeeklyTarget * (daysPassed / 7.0);

        if (expectedTillToday == 0) return 0;

        int score =
                (int) ((completedThisWeek * 100) / expectedTillToday);

        return Math.min(score, 100);
    }
}