package com.example.Streakify.repository;


import com.example.Streakify.model.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HabitLogRepository extends JpaRepository<HabitLog,Long> {
    Optional<HabitLog> findByHabitIdAndLogDate(Long habitId, LocalDate logDate);
    List<HabitLog> findByHabitId(Long habitId);
    List<HabitLog> findByHabitIdOrderByLogDateAsc(Long habitId);

    boolean existsByHabitIdAndLogDateAfter(Long id, LocalDate localDate);
}
