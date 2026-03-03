package com.example.Streakify.controller;

import com.example.Streakify.dto.HabitLogGetDTO;
import com.example.Streakify.dto.HabitLogRequestDTO;
import com.example.Streakify.dto.HabitLogResponseDTO;
import com.example.Streakify.service.HabitLogService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitLogController {
    private  final HabitLogService habitLogService;
    public HabitLogController(HabitLogService habitLogService){
        this.habitLogService=habitLogService;
    }

    @PostMapping("/{habitId}/logs")
    public HabitLogResponseDTO createLog(@PathVariable Long habitId,@Valid
                                         @RequestBody HabitLogRequestDTO dto){
        return habitLogService.createLog(habitId,dto.getDate(), dto.isCompleted());
    }

    @PutMapping("/{habitId}/logs/{date}")
    public HabitLogResponseDTO updateLog(
            @PathVariable Long habitId,
            @PathVariable LocalDate date,
            @RequestBody HabitLogRequestDTO dto) {

        return habitLogService.updateLog(
                habitId,
                date,
                dto.isCompleted()
        );
    }

    @GetMapping("/{habitId}/logs")
    public List<HabitLogGetDTO> getLogsByHabit(@PathVariable Long habitId){
        return habitLogService.getLogsByHabit(habitId);
    }
}
