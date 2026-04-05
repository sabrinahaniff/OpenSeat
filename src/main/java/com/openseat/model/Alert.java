package com.openseat.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private AppUser user;

    @ManyToOne(optional = false)
    private Course course;

    @Column(nullable = false, length = 500)
    private String message;

    private LocalDateTime createdAt;

    private boolean isRead;

    public Alert() {}

    public Alert(AppUser user, Course course, String message) {
        this.user = user;
        this.course = course;
        this.message = message;
        this.createdAt = LocalDateTime.now();
        this.isRead = false;
    }

    public Long getId() { return id; }
    public AppUser getUser() { return user; }
    public Course getCourse() { return course; }
    public String getMessage() { return message; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public boolean isRead() { return isRead; }

    public void setId(Long id) { this.id = id; }
    public void setUser(AppUser user) { this.user = user; }
    public void setCourse(Course course) { this.course = course; }
    public void setMessage(String message) { this.message = message; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setRead(boolean read) { isRead = read; }
}