package com.jayanth.habittracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "habits")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String habitName;
    private String description;
    private Boolean completed = false;
    private String lastCompletedDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Habit() {}

    public Long getId() { return id; }
    public String getHabitName() { return habitName; }
    public String getDescription() { return description; }
    public Boolean getCompleted() { return completed; }
    public String getLastCompletedDate() { return lastCompletedDate; }
    public User getUser() { return user; }

    public void setId(Long id) { this.id = id; }
    public void setHabitName(String habitName) { this.habitName = habitName; }
    public void setDescription(String description) { this.description = description; }
    public void setCompleted(Boolean completed) { this.completed = completed; }
    public void setLastCompletedDate(String lastCompletedDate) { this.lastCompletedDate = lastCompletedDate; }
    public void setUser(User user) { this.user = user; }
}
