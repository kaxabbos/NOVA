package com.nova.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainCont extends Main {

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("role", checkUserRole());
        return "about";
    }

    @GetMapping
    public String index1(Model model) {
        model.addAttribute("role", checkUserRole());
        return "index";
    }

    @GetMapping("/index")
    public String index2(Model model) {
        model.addAttribute("role", checkUserRole());
        return "index";
    }
}