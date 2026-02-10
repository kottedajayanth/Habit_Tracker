package com.jayanth.habittracker.repository;

import com.jayanth.habittracker.entity.Habit;
import com.jayanth.habittracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findByUser(User user);
}
