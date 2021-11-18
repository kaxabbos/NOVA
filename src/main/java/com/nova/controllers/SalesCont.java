package com.nova.controllers;

import com.nova.models.Games;
import com.nova.models.Users;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SalesCont extends Main {

    @GetMapping("/sales")
    public String sales(Model model) {
        Users userFromDB = new Users();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            if (userDetail != null) userFromDB = repoUsers.findByUsername(userDetail.getUsername());
        }

        List<Games> games = repoGames.findAllByUserid(userFromDB.getId());
        float income = 0;
        for (Games g : games) income += g.getIncome();

        model.addAttribute("income", income);
        model.addAttribute("games", games);
        model.addAttribute("role", checkUserRole());
        return "sales";
    }
}
