package io.github.harshanabandara.dashboard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
    private long userId;
    private String firstName;
    private String lastName;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "last_seen_at")
    private LocalDateTime last_seen_at;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        last_seen_at = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        last_seen_at = LocalDateTime.now();
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLast_seen_at() {
        return last_seen_at;
    }

    public void setLast_seen_at(LocalDateTime last_seen_at) {
        this.last_seen_at = last_seen_at;
    }
}
