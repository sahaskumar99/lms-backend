package com.jwt.example.controller;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.example.model.User;
// import com.jwt.example.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/hello")
public class HomeController {
    // @Autowired
    // private UserService userService;

    // Logger logger = LoggerFactory.getLogger(HomeController.class);
    @GetMapping("/users")
    public String getUser() {
        // this.logger.warn("This is working message");
        // System.out.println("Method");
        return "Hello";
    }


}