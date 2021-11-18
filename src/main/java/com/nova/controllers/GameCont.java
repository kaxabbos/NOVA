package com.nova.controllers;

import com.nova.models.Comments;
import com.nova.models.Games;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class GameCont extends Main {

    @GetMapping("/game/{id}")
    public String game(@PathVariable(value = "id") Long id, Model model) {
        if (!repoGames.existsById(id)) return "redirect:/catalog";
        long userid = 0, userIdFromBD, gameid = 0, cart = 1, buy = 1;
        Users userFromDB = new Users();

        Optional<Games> temp = repoGames.findById(id);
        List<Games> games = new ArrayList<>();
        temp.ifPresent(games::add);

        for (Games g : games) {
            userid = g.getUserid();
            gameid = g.getId();
            break;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            if (userDetail != null) userFromDB = repoUsers.findByUsername(userDetail.getUsername());
        }

        userIdFromBD = userFromDB.getId();
        if (userIdFromBD == userid) model.addAttribute("userid", userid);

        List<Comments> comments = repoComments.findAllByGameid(gameid);
        if (userFromDB.getCart() != null) {
            long[] carts = userFromDB.getCart();
            for (long c : carts)
                if (c == id) {
                    model.addAttribute("cart", cart);
                    break;
                }
        }

        if (userFromDB.getBuy() != null) {
            long[] buys = userFromDB.getBuy();
            for (long b : buys)
                if (b == id) {
                    model.addAttribute("buy", buy);
                    break;
                }
        }

        model.addAttribute("games", games);
        model.addAttribute("comments", comments);
        model.addAttribute("role", checkUserRole());
        return "game";
    }

    @PostMapping("/game/{id}/comment_add")
    public String comment_add(
            @PathVariable(value = "id") Long id,
            @RequestParam String date, @RequestParam String[] comment
    ) {
        StringBuilder com = new StringBuilder();
        for (String s : comment) com.append(s);

        String username = "";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            if (userDetail != null) {
                Users userFromDB = repoUsers.findByUsername(userDetail.getUsername());
                username = userFromDB.getUsername();
            }
        }

        Comments c = new Comments(id, username, date, com.toString());

        repoComments.save(c);
        return "redirect:/game/{id}";
    }
}