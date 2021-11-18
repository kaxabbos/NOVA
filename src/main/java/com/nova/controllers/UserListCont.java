package com.nova.controllers;

import com.nova.models.Games;
import com.nova.models.enums.Role;
import com.nova.models.Users;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserListCont extends Main {

    @GetMapping("/userList")
    public String userList(Model model) {
        Users userFromDB = new Users();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            if (userDetail != null) userFromDB = repoUsers.findByUsername(userDetail.getUsername());
        }

        List<Games> games = repoGames.findAllByUserid(userFromDB.getId());
        float income = 0;
        for (Games g : games) income += g.getIncome();

        List<Users> users = repoUsers.findAll();

        model.addAttribute("income", income);
        model.addAttribute("games", games);
        model.addAttribute("users", users);
        model.addAttribute("role", checkUserRole());
        return "userList";
    }

    @PostMapping("/userList/{id}/edit")
    public String userUpdate(
            @PathVariable(value = "id") Long id,@RequestParam String username,
            @RequestParam String password, @RequestParam Role role
    ) {
        Users temp = repoUsers.findById(id).orElseThrow();
        temp.setUsername(username);
        temp.setPassword(password);
        temp.setRole(role);
        repoUsers.save(temp);
        return "redirect:/userList";
    }

    @PostMapping("/userList/{id}/delete")
    public String userDelete(@PathVariable(value = "id") Long id) {
        repoUsers.deleteById(id);
        return "redirect:/userList";
    }
}
