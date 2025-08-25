package com.jwt.example.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/dashboard")
    public String studentDashboard() {
        return "Welcome Student! You can see your courses here.";
    }

    @GetMapping("/courses")
    public String viewCourses() {
        return "List of courses available for student.";
    }
}
