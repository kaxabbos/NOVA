package com.nova.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCont extends Global {

    @GetMapping
    public String index1(Model model) {
        deleteNull();
        model.addAttribute("role", checkUserRole());
        return "index";
    }

    @GetMapping("/index")
    public String index2(Model model) {
        deleteNull();
        model.addAttribute("role", checkUserRole());
        return "index";
    }
}