package com.example.Streakify.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "habit_logs",uniqueConstraints=@UniqueConstraint(columnNames = {"habit_id","log_date"}))
public class HabitLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "log_date",nullable = false)
    private LocalDate logDate;

    @Column(nullable = false)
    private Boolean completed=true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id" ,nullable = false)
    private Habit habit;

}
