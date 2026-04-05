package com.openseat.repository;

import com.openseat.model.AppUser;
import com.openseat.model.Course;
import com.openseat.model.WatchlistEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchlistEntryRepository extends JpaRepository<WatchlistEntry, Long> {
    List<WatchlistEntry> findByUser(AppUser user);
    List<WatchlistEntry> findByCourse(Course course);
    boolean existsByUserAndCourse(AppUser user, Course course);
    void deleteByIdAndUser(Long id, AppUser user);
}