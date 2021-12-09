package com.nova.controllers;

import com.nova.models.Games;
import com.nova.models.Users;
import com.nova.models.enums.GBMB;
import com.nova.models.enums.Genre;
import com.nova.models.enums.Language;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class GameAddEditCont extends Main {

    @GetMapping("/game/add")
    public String game_add(Model model) {
        model.addAttribute("role", checkUserRole());
        return "game_add";
    }

    @PostMapping("game/add")
    public String add(
            @RequestParam String name, @RequestParam String dev, @RequestParam("poster") MultipartFile poster,
            @RequestParam("screenshots") MultipartFile[] screenshots, @RequestParam String pub, @RequestParam int year,
            @RequestParam float version, @RequestParam float price, @RequestParam Genre genre,
            @RequestParam Language language, @RequestParam String os, @RequestParam String proc,
            @RequestParam String videocard, @RequestParam String sound, @RequestParam int ram,
            @RequestParam int place, @RequestParam GBMB switchram, @RequestParam GBMB switchplace,
            @RequestParam String[] description, @RequestParam String file
    ) throws IOException {
        Games newGames = new Games(
                name, dev, pub, year, version, price, genre, language, os, proc, videocard, sound, ram, place,
                switchram, switchplace, file
        );

        StringBuilder des = new StringBuilder();
        for (String s : description) des.append(s);
        newGames.setDescription(des.toString());
        String uuidFile = UUID.randomUUID().toString();

        if (poster != null && !poster.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            String result_poster = uuidFile + "_" + poster.getOriginalFilename();
            poster.transferTo(new File(uploadPath + "/" + result_poster));
            newGames.setPoster(result_poster);
        }

        if (screenshots != null && !screenshots[0].getOriginalFilename().isEmpty()) {
            uuidFile = UUID.randomUUID().toString();
            String result_screenshot;
            String[] result_screenshots = new String[screenshots.length];
            for (int i = 0; i < result_screenshots.length; i++) {
                result_screenshot = uuidFile + "_" + screenshots[i].getOriginalFilename();
                screenshots[i].transferTo(new File(uploadPath + "/" + result_screenshot));
                result_screenshots[i] = result_screenshot;
            }
            newGames.setScreenshots(result_screenshots);
        }

        newGames.setUserid(checkUser().getId());

        repoGames.save(newGames);
        return "redirect:/catalog/all";
    }

    @GetMapping("/game/{id}/edit")
    public String game_edit(@PathVariable(value = "id") Long id, Model model) {
        if (!repoGames.existsById(id)) return "redirect:/catalog";
        Optional<Games> temp = repoGames.findById(id);
        ArrayList<Games> games = new ArrayList<>();
        temp.ifPresent(games::add);
        model.addAttribute("games", games);
        model.addAttribute("role", checkUserRole());
        return "game_edit";
    }

    @PostMapping("/game/{id}/edit")
    public String edit(
            @PathVariable(value = "id") Long id,
            @RequestParam String name, @RequestParam String dev, @RequestParam("poster") MultipartFile poster,
            @RequestParam("screenshots") MultipartFile[] screenshots, @RequestParam String pub, @RequestParam int year,
            @RequestParam float version, @RequestParam float price, @RequestParam Genre genre,
            @RequestParam Language language, @RequestParam String os, @RequestParam String proc,
            @RequestParam String videocard, @RequestParam String sound, @RequestParam int ram,
            @RequestParam int place, @RequestParam GBMB switchram, @RequestParam GBMB switchplace,
            @RequestParam String[] description, @RequestParam String file
    ) throws IOException {
        Games g = repoGames.findById(id).orElseThrow();
        StringBuilder des = new StringBuilder();
        for (String s : description) des.append(s);

        g.setDescription(des.toString());
        g.setName(name);
        g.setDev(dev);
        g.setPub(pub);
        g.setYear(year);
        g.setVersion(version);
        g.setPrice(price);
        g.setGenre(genre);
        g.setLanguage(language);
        g.setOs(os);
        g.setProc(proc);
        g.setVideocard(videocard);
        g.setSound(sound);
        g.setRam(ram);
        g.setPlace(place);
        g.setSwitchram(switchram);
        g.setSwitchplace(switchplace);
        g.setFile(file);
        String uuidFile = UUID.randomUUID().toString();

        if (poster != null && !poster.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            String result_poster = uuidFile + "_" + poster.getOriginalFilename();
            poster.transferTo(new File(uploadPath + "/" + result_poster));
            g.setPoster(result_poster);
        }

        if (screenshots != null && !screenshots[0].getOriginalFilename().isEmpty()) {
            uuidFile = UUID.randomUUID().toString();
            String result_screenshot;
            String[] result_screenshots = new String[screenshots.length];
            for (int i = 0; i < result_screenshots.length; i++) {
                result_screenshot = uuidFile + "_" + screenshots[i].getOriginalFilename();
                screenshots[i].transferTo(new File(uploadPath + "/" + result_screenshot));
                result_screenshots[i] = result_screenshot;
            }
            g.setScreenshots(result_screenshots);
        }

        repoGames.save(g);
        return "redirect:/game/{id}/";
    }

    @GetMapping("/game/{id}/delete")
    public String game_delete(@PathVariable(value = "id") Long id) {
        repoGames.deleteById(id);
        List<Users> users = repoUsers.findAll();

        for (Users user : users)
            if (user.getCart() != null)
                for (long carts : user.getCart())
                    if (id == carts) {
                        if (user.getCart().length == 1) user.setCart(null);
                        else {
                            long[] cart = new long[user.getCart().length - 1];
                            int i = 0;
                            for (long c : user.getCart()) {
                                if (id == c) continue;
                                cart[i] = c;
                                i++;
                            }
                            user.setCart(cart);
                        }
                    }

        for (Users user : users) repoUsers.save(user);
        return "redirect:/catalog/all";
    }
}
