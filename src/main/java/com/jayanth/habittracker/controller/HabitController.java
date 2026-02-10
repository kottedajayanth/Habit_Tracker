package com.jayanth.habittracker.controller;

import com.jayanth.habittracker.entity.Habit;
import com.jayanth.habittracker.entity.User;
import com.jayanth.habittracker.repository.HabitRepository;
import com.jayanth.habittracker.repository.UserRepository;
import com.jayanth.habittracker.security.SecurityUtil;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@CrossOrigin(origins = "http://localhost:3000")
public class HabitController {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitController(HabitRepository habitRepository,
                            UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
    }

    // ✅ GET habits
    @GetMapping
    public List<Habit> getHabits() {

        String email = SecurityUtil.getCurrentUserEmail();
        if (email == null) {
            throw new RuntimeException("Unauthorized");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return habitRepository.findByUser(user);
    }

    // ✅ CREATE habit
    @PostMapping
    public Habit createHabit(@RequestBody Habit habit) {

        String email = SecurityUtil.getCurrentUserEmail();
        if (email == null) {
            throw new RuntimeException("Unauthorized");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        habit.setId(null);      // important
        habit.setUser(user);

        return habitRepository.save(habit);
    }

    // ✅ DELETE habit
    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable Long id) {

        String email = SecurityUtil.getCurrentUserEmail();
        if (email == null) {
            throw new RuntimeException("Unauthorized");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));

        if (!habit.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        habitRepository.delete(habit);
    }
}
