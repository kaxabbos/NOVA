package com.nova.controllers;

import com.nova.models.Games;
import com.nova.models.enums.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CatalogCont extends Global {

    @GetMapping("/catalog/all")
    public String catalog_main(Model model) {
        List<Games> games = repoGames.findAll();
        model.addAttribute("games", games);
        model.addAttribute("role", checkUserRole());
        return "catalog";
    }

    @PostMapping("/catalog/game_search")
    public String catalog_search(@RequestParam Genre genre, @RequestParam String date_range, Model model) {
        List<Games> games = new ArrayList<>(), res = new ArrayList<>(), temp;

        if (genre == Genre.Все) {
            games = repoGames.findAll();
        } else if (genre == Genre.Бесплатные) {
            temp = repoGames.findAll();
            for (Games g : temp) if (g.getPrice() == 0) games.add(g);
        } else if (genre == Genre.Платные) {
            temp = repoGames.findAll();
            for (Games g : temp) if (g.getPrice() != 0) games.add(g);
        } else {
            games = repoGames.findAllByGenre(genre);
        }

        String[] date = date_range.split(" ");
        int with = Integer.parseInt(date[0]), before = Integer.parseInt(date[2]);

        for (Games g : games) {
            System.out.println("1 " + g.getYear());
            if (with <= g.getYear() && g.getYear() <= before) {
                res.add(g);
                System.out.println("2 " + g.getYear());
            }
        }

        model.addAttribute("games", res);
        model.addAttribute("role", checkUserRole());
        return "catalog";
    }

    @GetMapping("/catalog/genre/{genre}")
    public String catalog_genre_search(@PathVariable(value = "genre") Genre genre, Model model) {
        List<Games> games, res = new ArrayList<>();
        if (genre == Genre.Бесплатные) {
            games = repoGames.findAll();
            for (Games g : games) if (g.getPrice() == 0) res.add(g);
        } else if (genre == Genre.Платные) {
            games = repoGames.findAll();
            for (Games g : games) if (g.getPrice() != 0) res.add(g);
        } else res = repoGames.findAllByGenre(genre);

        model.addAttribute("games", res);
        model.addAttribute("role", checkUserRole());
        return "catalog";
    }

    @GetMapping("/catalog/year/{year}")
    public String catalog_year_search(@PathVariable(value = "year") int year, Model model) {
        List<Games> games = repoGames.findAllByYear(year);
        model.addAttribute("games", games);
        model.addAttribute("role", checkUserRole());
        return "catalog";
    }

    @PostMapping("/catalog/search")
    public String catalogSearch(@RequestParam String search, Model model) {
        List<Games> temp = repoGames.findAll(), games = new ArrayList<>();
        for (Games g : temp) if (g.getName().contains(search)) games.add(g);
        model.addAttribute("games", games);
        model.addAttribute("role", checkUserRole());
        return "catalog";
    }
}
