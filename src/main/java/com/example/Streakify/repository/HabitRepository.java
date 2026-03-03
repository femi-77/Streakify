package com.example.Streakify.repository;

import com.example.Streakify.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<Habit,Long> {
    List<Habit> findByUserId(Long userId);
    boolean existsByUserIdAndName(Long userId, String name);
}
