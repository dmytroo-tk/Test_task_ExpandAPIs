package com.example.test_task_expandapis.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/ss")
    public String ss() {
        return "ss";
    }
}
