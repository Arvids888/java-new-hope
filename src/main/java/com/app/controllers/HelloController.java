package com.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {

    @GetMapping("/hello/{userName}") // http://localhost:8080/hello/Dmitrijs
    public String getHelloPage(@PathVariable(value = "userName") String userName, Model model) {
        model.addAttribute("name", userName);
        return "hello";
    }

}
