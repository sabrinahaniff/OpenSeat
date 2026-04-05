package com.openseat.service;

import com.openseat.model.Alert;
import com.openseat.model.AppUser;
import com.openseat.model.Course;
import com.openseat.model.WatchlistEntry;
import com.openseat.repository.AlertRepository;
import com.openseat.repository.CourseRepository;
import com.openseat.repository.WatchlistEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final WatchlistEntryRepository watchlistEntryRepository;
    private final AlertRepository alertRepository;
    private final AppUserService appUserService;

    public CourseService(CourseRepository courseRepository,
                         WatchlistEntryRepository watchlistEntryRepository,
                         AlertRepository alertRepository,
                         AppUserService appUserService) {
        this.courseRepository = courseRepository;
        this.watchlistEntryRepository = watchlistEntryRepository;
        this.alertRepository = alertRepository;
        this.appUserService = appUserService;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAllByOrderByCourseCodeAsc();
    }

    public List<WatchlistEntry> getCurrentUserWatchlist() {
        return watchlistEntryRepository.findByUser(appUserService.getCurrentUser());
    }

    public Set<Long> getCurrentUserWatchedCourseIds() {
        return getCurrentUserWatchlist().stream()
                .map(entry -> entry.getCourse().getId())
                .collect(Collectors.toSet());
    }

    public void addCourseToWatchlist(Long courseId) {
        AppUser user = appUserService.getCurrentUser();
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        if (!watchlistEntryRepository.existsByUserAndCourse(user, course)) {
            watchlistEntryRepository.save(new WatchlistEntry(user, course));
        }
    }

    public void removeWatchlistEntry(Long watchlistEntryId) {
        AppUser user = appUserService.getCurrentUser();
        watchlistEntryRepository.deleteByIdAndUser(watchlistEntryId, user);
    }

    public List<Alert> getCurrentUserAlerts() {
        return alertRepository.findByUserOrderByCreatedAtDesc(appUserService.getCurrentUser());
    }

    public List<Alert> getRecentCurrentUserAlerts() {
        return alertRepository.findTop5ByUserOrderByCreatedAtDesc(appUserService.getCurrentUser());
    }

    public void updateCourseAvailability(Long courseId, int newSeats, String newRestrictionStatus) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        boolean wasUnavailable = course.getSeatsAvailable() <= 0 || !"OPEN".equalsIgnoreCase(course.getRestrictionStatus());

        course.setSeatsAvailable(newSeats);
        course.setRestrictionStatus(newRestrictionStatus);
        course.setLastUpdated(LocalDateTime.now());
        courseRepository.save(course);

        boolean isAvailableNow = course.getSeatsAvailable() > 0 && "OPEN".equalsIgnoreCase(course.getRestrictionStatus());

        if (wasUnavailable && isAvailableNow) {
            List<WatchlistEntry> watchers = watchlistEntryRepository.findByCourse(course);

            for (WatchlistEntry watcher : watchers) {
                String message = course.getCourseCode() + " is now open with "
                        + course.getSeatsAvailable() + " seat(s) available.";
                alertRepository.save(new Alert(watcher.getUser(), course, message));
            }
        }
    }
}