package com.openseat.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "watchlist_entries")
public class WatchlistEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private AppUser user;

    @ManyToOne(optional = false)
    private Course course;

    private LocalDateTime createdAt;

    public WatchlistEntry() {}

    public WatchlistEntry(AppUser user, Course course) {
        this.user = user;
        this.course = course;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public AppUser getUser() { return user; }
    public Course getCourse() { return course; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setUser(AppUser user) { this.user = user; }
    public void setCourse(Course course) { this.course = course; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}