package com.openseat.controller;

import com.openseat.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CourseService courseService;

    public AdminController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String adminCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "admin/courses";
    }

    @PostMapping("/courses/{id}/update")
    public String updateCourse(@PathVariable Long id,
                               @RequestParam int seatsAvailable,
                               @RequestParam String restrictionStatus,
                               RedirectAttributes redirectAttributes) {
        courseService.updateCourseAvailability(id, seatsAvailable, restrictionStatus);
        redirectAttributes.addFlashAttribute("successMessage", "Course availability updated.");
        return "redirect:/admin/courses";
    }
}