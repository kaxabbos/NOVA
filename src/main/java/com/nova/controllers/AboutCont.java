package com.nova.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutCont extends Global {

    @GetMapping("/about")
    public String about(Model model) {
        deleteNull();
        model.addAttribute("role", checkUserRole());
        return "about";
    }

}
