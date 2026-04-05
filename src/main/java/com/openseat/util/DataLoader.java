package com.openseat.util;

import com.openseat.model.AppUser;
import com.openseat.model.Course;
import com.openseat.repository.AppUserRepository;
import com.openseat.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(AppUserRepository appUserRepository,
                      CourseRepository courseRepository,
                      PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (appUserRepository.count() == 0) {
            appUserRepository.save(new AppUser(
                    "Student Demo",
                    "student@openseat.local",
                    passwordEncoder.encode("password123"),
                    "STUDENT"
            ));

            appUserRepository.save(new AppUser(
                    "Admin Demo",
                    "admin@openseat.local",
                    passwordEncoder.encode("admin123"),
                    "ADMIN"
            ));
        }

        if (courseRepository.count() == 0) {
    courseRepository.save(new Course("WDV-101", "Web Foundations Studio", 0, "CLOSED"));
    courseRepository.save(new Course("ATH-204", "Authentication & Access Control", 0, "RESTRICTED"));
    courseRepository.save(new Course("DBS-220", "Data Persistence Lab", 5, "OPEN"));
    courseRepository.save(new Course("API-305", "Service Architecture", 2, "OPEN"));
    courseRepository.save(new Course("CLD-310", "Cloud Deployment Studio", 1, "OPEN"));
    courseRepository.save(new Course("UXR-150", "Interface Design Basics", 0, "CLOSED"));
    }
  }
}