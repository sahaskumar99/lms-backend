package com.jwt.example.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @GetMapping("/dashboard")
    public String teacherDashboard() {
        return "Welcome Teacher! You can manage your courses here.";
    }

    @PostMapping("/course")
    public String addCourse() {
        return "Teacher can add a new course.";
    }
}
