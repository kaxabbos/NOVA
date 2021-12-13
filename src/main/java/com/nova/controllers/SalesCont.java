package com.nova.controllers;

import com.nova.models.GameIncome;
import com.nova.models.Games;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SalesCont extends Main {

    @GetMapping("/sales")
    public String sales(Model model) {
        List<GameIncome> gameIncomes = repoGameIncome.findAllByUserid(checkUser().getId());
        float income = 0;
        for (GameIncome g : gameIncomes) income += g.getIncome();

        model.addAttribute("income", income);
        model.addAttribute("games", gameIncomes);
        model.addAttribute("role", checkUserRole());
        return "sales";
    }
}
