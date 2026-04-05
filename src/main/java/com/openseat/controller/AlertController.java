package com.openseat.controller;

import com.openseat.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlertController {

    private final CourseService courseService;

    public AlertController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/alerts")
    public String alerts(Model model) {
        model.addAttribute("alerts", courseService.getCurrentUserAlerts());
        return "alerts/index";
    }
}