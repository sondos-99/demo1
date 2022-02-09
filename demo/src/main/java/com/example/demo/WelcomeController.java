package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;

@Controller
public class WelcomeController {
    @GetMapping("/welcome")
    public String welcome(WebRequest request, Model model) throws IOException {
           model.addAttribute("message","Hello World");
           return "welcome";
    }
}
