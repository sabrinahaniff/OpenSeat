package com.openseat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String courseCode;

    @NotBlank
    private String courseName;

    @Min(0)
    private int seatsAvailable;

    @Column(nullable = false)
    private String restrictionStatus; // OPEN, RESTRICTED, CLOSED

    private LocalDateTime lastUpdated;

    public Course() {}

    public Course(String courseCode, String courseName, int seatsAvailable, String restrictionStatus) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.seatsAvailable = seatsAvailable;
        this.restrictionStatus = restrictionStatus;
        this.lastUpdated = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getSeatsAvailable() { return seatsAvailable; }
    public String getRestrictionStatus() { return restrictionStatus; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }

    public void setId(Long id) { this.id = id; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }
    public void setRestrictionStatus(String restrictionStatus) { this.restrictionStatus = restrictionStatus; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}