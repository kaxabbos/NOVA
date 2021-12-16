package com.nova.controllers;

import com.nova.models.GameDescription;
import com.nova.models.GameIncome;
import com.nova.models.Games;
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
import java.util.*;

@Controller
public class GameAddEditCont extends Main {

    @GetMapping("/game/add")
    public String game_add(Model model) {
        if (checkUserRole().equals("USER")) return "redirect:/index";
        model.addAttribute("role", checkUserRole());
        return "game_add";
    }

    @PostMapping("game/add")
    public String add(
            Model model, @RequestParam String name, @RequestParam String dev,
            @RequestParam("poster") MultipartFile poster, @RequestParam("screenshots") MultipartFile[] screenshots,
            @RequestParam String pub, @RequestParam int year, @RequestParam float version, @RequestParam float price,
            @RequestParam Genre genre, @RequestParam Language language, @RequestParam String os, @RequestParam String proc,
            @RequestParam String videocard, @RequestParam String sound, @RequestParam int ram, @RequestParam int place,
            @RequestParam GBMB switchram, @RequestParam GBMB switchplace, @RequestParam String[] description, @RequestParam String file
    ) {
        Games newGames = new Games(name, year, price, genre);
        GameDescription newGameDescription = new GameDescription(dev, pub, os, proc, videocard, sound, file, ram, place, version, switchram, switchplace, language);
        GameIncome newGameIncome = new GameIncome(name, price);
        boolean createDir = true;

        StringBuilder des = new StringBuilder();
        for (String s : description) des.append(s);
        newGameDescription.setDescription(des.toString());

        String uuidFile = UUID.randomUUID().toString();

        if (poster != null && !Objects.requireNonNull(poster.getOriginalFilename()).isEmpty()) {
            String result_poster = "";
            try {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    result_poster = uuidFile + "_" + poster.getOriginalFilename();
                    poster.transferTo(new File(uploadPath + "/" + result_poster));
                }
            } catch (IOException e) {
                model.addAttribute("message", "Ошибка, слишком большой размер постера!!!");
                return "/";
            }
            newGames.setPoster(result_poster);
        }

        if (screenshots != null && !Objects.requireNonNull(screenshots[0].getOriginalFilename()).isEmpty()) {
            String result_screenshot;
            String[] result_screenshots;
            try {
                uuidFile = UUID.randomUUID().toString();
                result_screenshots = new String[screenshots.length];
                for (int i = 0; i < result_screenshots.length; i++) {
                    result_screenshot = uuidFile + "_" + screenshots[i].getOriginalFilename();
                    screenshots[i].transferTo(new File(uploadPath + "/" + result_screenshot));
                    result_screenshots[i] = result_screenshot;
                }
            } catch (IOException e) {
                model.addAttribute("message", "Ошибка, слишком большой размер скриншотов");
                return "/";
            }
            newGameDescription.setScreenshots(result_screenshots);
        }

        long id = checkUser().getId();
        newGames.setUserid(id);

        repoGames.save(newGames);

        long i = lastIndexGames();

        newGameDescription.setGameid(i);
        newGameIncome.setGameid(i);
        newGameIncome.setUserid(id);

        repoGameDescription.save(newGameDescription);
        repoGameIncome.save(newGameIncome);

        return "redirect:/catalog/all";
    }

    @GetMapping("/game/{id}/edit")
    public String game_edit(@PathVariable(value = "id") Long id, Model model) {
        if (checkUserRole().equals("USER")) return "redirect:/index";
        if (!repoGames.existsById(id)) return "redirect:/catalog";
        Optional<Games> temp = repoGames.findById(id);
        List<Games> games = new ArrayList<>();
        temp.ifPresent(games::add);
        model.addAttribute("games", games);
        model.addAttribute("role", checkUserRole());
        return "game_edit";
    }

    @PostMapping("/game/{id}/edit")
    public String edit(Model model, @PathVariable(value = "id") Long id, @RequestParam String name, @RequestParam String dev, @RequestParam("poster") MultipartFile poster, @RequestParam("screenshots") MultipartFile[] screenshots, @RequestParam String pub, @RequestParam int year, @RequestParam float version, @RequestParam float price, @RequestParam Genre genre, @RequestParam Language language, @RequestParam String os, @RequestParam String proc, @RequestParam String videocard, @RequestParam String sound, @RequestParam int ram, @RequestParam int place, @RequestParam GBMB switchram, @RequestParam GBMB switchplace, @RequestParam String[] description, @RequestParam String file) {
        Games g = repoGames.findById(id).orElseThrow();
        GameDescription gd = repoGameDescription.findByGameid(id);
        boolean createDir = true;

        StringBuilder des = new StringBuilder();
        for (String s : description) des.append(s);

        gd.setDescription(des.toString());
        g.setName(name);
        gd.setDev(dev);
        gd.setPub(pub);
        g.setYear(year);
        gd.setVersion(version);
        g.setPrice(price);
        g.setGenre(genre);
        gd.setLanguage(language);
        gd.setOs(os);
        gd.setProc(proc);
        gd.setVideocard(videocard);
        gd.setSound(sound);
        gd.setRam(ram);
        gd.setPlace(place);
        gd.setSwitchram(switchram);
        gd.setSwitchplace(switchplace);
        gd.setFile(file);

        String uuidFile = UUID.randomUUID().toString();

        if (poster != null && !Objects.requireNonNull(poster.getOriginalFilename()).isEmpty()) {
            String result_poster = "";
            try {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    result_poster = uuidFile + "_" + poster.getOriginalFilename();
                    poster.transferTo(new File(uploadPath + "/" + result_poster));
                }
            } catch (IOException e) {
                model.addAttribute("message", "Ошибка слишком большой размер постера");
                return "/";
            }
            g.setPoster(result_poster);
        }

        if (screenshots != null && !Objects.requireNonNull(screenshots[0].getOriginalFilename()).isEmpty()) {
            String result_screenshot;
            String[] result_screenshots;
            try {
                uuidFile = UUID.randomUUID().toString();
                result_screenshots = new String[screenshots.length];
                for (int i = 0; i < result_screenshots.length; i++) {
                    result_screenshot = uuidFile + "_" + screenshots[i].getOriginalFilename();
                    screenshots[i].transferTo(new File(uploadPath + "/" + result_screenshot));
                    result_screenshots[i] = result_screenshot;
                }
            } catch (IOException e) {
                model.addAttribute("message", "Ошибка слишком большой размер скриншотов");
                return "/";
            }
            gd.setScreenshots(result_screenshots);
        }

        repoGames.save(g);
        repoGameDescription.save(gd);
        return "redirect:/game/{id}/";
    }

    @GetMapping("/game/{id}/delete")
    public String game_delete(@PathVariable(value = "id") Long id) {
        if (!checkUserRole().equals("USER")) return "redirect:/index";
        totalGameDelete(id);
        return "redirect:/catalog/all";
    }
}
