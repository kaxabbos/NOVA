package com.nova.controllers;

import com.nova.models.GameIncome;
import com.nova.models.Games;
import com.nova.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartCont extends Global {

    @GetMapping("/cart")
    public String cart(Model model) {
        Users userFromDB = checkUser();

        if (userFromDB.getCart() != null) {
            long[] cart = userFromDB.getCart();
            float summary = 0;
            List<Games> games = new ArrayList<>(), temp = repoGames.findAll();

            for (Games g : temp) for (long c : cart) if (g.getId() == c) games.add(g);
            for (Games g : games) summary += g.getPrice();

            model.addAttribute("summary", summary);
            model.addAttribute("games", games);
            int i = 0;
            for (Games g : games) {
                i++;
                if (i == 2) {
                    model.addAttribute("more", i);
                    break;
                }
            }
        }

        model.addAttribute("role", checkUserRole());
        return "cart";
    }

    @PostMapping("/game/{id}/add_cart")
    public String add_cart(@PathVariable(value = "id") Long id) {
        Users userFromDB = checkUser();

        long[] cart;
        if (userFromDB.getCart() == null) cart = new long[]{id};
        else {
            cart = new long[userFromDB.getCart().length + 1];
            for (int i = 0; i < userFromDB.getCart().length; i++) cart[i] = userFromDB.getCart()[i];
            cart[userFromDB.getCart().length] = id;
        }

        userFromDB.setCart(cart);

        repoUsers.save(userFromDB);
        return "redirect:/game/{id}";
    }

    @PostMapping("/game/{id}/remove_cart")
    public String remove_cart(@PathVariable(value = "id") Long id) {
        Users userFromDB = checkUser();

        if (userFromDB.getCart().length == 1) userFromDB.setCart(null);
        else {
            long[] cart = new long[userFromDB.getCart().length - 1];
            int i = 0;
            for (long c : userFromDB.getCart()) {
                if (id == c) continue;
                cart[i] = c;
                i++;
            }
            userFromDB.setCart(cart);
        }

        repoUsers.save(userFromDB);
        return "redirect:/cart";
    }

    @PostMapping("/game/remove_cart_all")
    public String remove_cart_all(Model model) {
        Users userFromDB = checkUser();

        userFromDB.setCart(null);

        repoUsers.save(userFromDB);
        return "redirect:/cart";
    }

    @PostMapping("/game/{id}/buy")
    public String buy(@PathVariable(value = "id") Long id) {
        Users userFromDB = checkUser();

        if (userFromDB.getCart() != null) {
            if (userFromDB.getCart().length == 1) userFromDB.setCart(null);
            else {
                long[] cart = new long[userFromDB.getCart().length - 1];
                int i = 0;
                for (long c : userFromDB.getCart()) {
                    if (id == c) continue;
                    cart[i] = c;
                    i++;
                }
                userFromDB.setCart(cart);
            }
        }

        long[] buy;
        if (userFromDB.getBuy() == null) buy = new long[]{id};
        else {
            buy = new long[userFromDB.getBuy().length + 1];
            for (int i = 0; i < userFromDB.getBuy().length; i++) buy[i] = userFromDB.getBuy()[i];
            buy[userFromDB.getBuy().length] = id;
        }
        userFromDB.setBuy(buy);

        GameIncome gi = repoGameIncome.findByGameid(id);

        gi.setCount(gi.getCount() + 1);
        gi.setIncome(gi.getIncome() + gi.getPrice());


        repoGameIncome.save(gi);
        repoUsers.save(userFromDB);
        return "redirect:/game/{id}";
    }

    @PostMapping("/game/buy_cart_all")
    public String buy_cart_all(Model model) {
        Users userFromDB = checkUser();

        long[] buy;
        if (userFromDB.getBuy() == null) {
            buy = new long[userFromDB.getCart().length];
            for (int i = 0; i < userFromDB.getCart().length; i++) {
                buy[i] = userFromDB.getCart()[i];

                GameIncome g = repoGameIncome.findByGameid(userFromDB.getCart()[i]);
                g.setCount(g.getCount() + 1);
                g.setIncome(g.getIncome() + g.getPrice());
                repoGameIncome.save(g);
            }

        } else {
            buy = new long[userFromDB.getBuy().length + userFromDB.getCart().length];
            for (int i = 0; i < buy.length; i++) {
                for (int j = 0; j < userFromDB.getBuy().length; j++) {
                    buy[i] = userFromDB.getBuy()[j];
                    i++;
                }
                for (int j = 0; j < userFromDB.getCart().length; j++) {
                    buy[i] = userFromDB.getCart()[j];
                    GameIncome g = repoGameIncome.findByUserid(userFromDB.getCart()[j]);
                    g.setCount(g.getCount() + 1);
                    g.setIncome(g.getIncome() + g.getPrice());
                    repoGameIncome.save(g);
                    i++;
                }
            }
        }

        userFromDB.setBuy(buy);
        userFromDB.setCart(null);

        repoUsers.save(userFromDB);

        return "redirect:/cart";
    }
}
