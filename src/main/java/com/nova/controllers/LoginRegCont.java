package com.nova.controllers;

import com.nova.models.Users;
import com.nova.models.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginRegCont extends Global {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("role", checkUserRole());
        return "login";
    }

    @GetMapping("/reg")
    public String reg(Model model) {
        model.addAttribute("role", checkUserRole());
        return "reg";
    }

    @PostMapping("/reg")
    public String addUser(Users user, Model model) {
        model.addAttribute("role", checkUserRole());
        Users userFromDB = repoUsers.findByUsername(user.getUsername());
        if (userFromDB != null) {
            model.addAttribute("message", "Пользователь c таким именем уже существует существует!");
            return "reg";
        }
        user.setRole(Role.USER);
        repoUsers.save(user);

        return "redirect:/login";
    }
}
