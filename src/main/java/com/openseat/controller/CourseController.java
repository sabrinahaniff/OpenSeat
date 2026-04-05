package com.openseat.controller;

import com.openseat.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("watchedCourseIds", courseService.getCurrentUserWatchedCourseIds());
        return "courses/index";
    }

    @PostMapping("/courses/{id}/watch")
    public String watchCourse(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        courseService.addCourseToWatchlist(id);
        redirectAttributes.addFlashAttribute("successMessage", "Course added to your watchlist.");
        return "redirect:/courses";
    }

    @GetMapping("/watchlist")
    public String watchlist(Model model) {
        model.addAttribute("watchlistEntries", courseService.getCurrentUserWatchlist());
        return "watchlist/index";
    }

    @PostMapping("/watchlist/{id}/remove")
    public String removeWatchlistEntry(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        courseService.removeWatchlistEntry(id);
        redirectAttributes.addFlashAttribute("successMessage", "Course removed from your watchlist.");
        return "redirect:/watchlist";
    }
}