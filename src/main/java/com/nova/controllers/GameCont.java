package com.nova.controllers;

import com.nova.models.GameComments;
import com.nova.models.Games;
import com.nova.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class GameCont extends Main {

    @GetMapping("/game/{id}")
    public String game(@PathVariable(value = "id") Long id, Model model) {
        if (!repoGames.existsById(id)) return "redirect:/catalog/all";


        long userid = 0, userIdFromBD, gameid = 0, cart = 1, buy = 1;
        Users userFromDB = checkUser();

        Optional<Games> temp = repoGames.findById(id);
        List<Games> games = new ArrayList<>();
        temp.ifPresent(games::add);

        for (Games g : games) {
            userid = g.getUserid();
            gameid = g.getId();
            g.addDescription(repoGameDescription.findByGameid(id));
            break;
        }

        userIdFromBD = userFromDB.getId();
        if (userIdFromBD == userid) model.addAttribute("userid", userid);

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

        List<GameComments> comments = repoComments.findAllByGameid(gameid);

        Collections.reverse(comments);

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

        GameComments c = new GameComments(id, checkUser().getUsername(), date, com.toString());

        repoComments.save(c);

        return "redirect:/game/{id}";
    }
}
