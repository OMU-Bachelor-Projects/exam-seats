package com.faculty.exam_seat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/merhaba")
    public String sayHello() {
        return "Spring Boot Merhaba!";
    }
}