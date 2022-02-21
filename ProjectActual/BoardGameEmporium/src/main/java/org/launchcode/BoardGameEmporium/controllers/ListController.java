package org.launchcode.BoardGameEmporium.controllers;


import org.launchcode.BoardGameEmporium.models.Game;
import org.launchcode.BoardGameEmporium.models.GameData;
import org.launchcode.BoardGameEmporium.models.data.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    private GameRepository gameRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("all","All");

    }

    @RequestMapping("")
        public String list(Model model) {

        return "list";
        }

    @RequestMapping(value = "games")
    public String listGamesByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Game> games;
        if (column.toLowerCase().equals("all")) {
            games = gameRepository.findAll();
            model.addAttribute("title", "All Games");
        } else {
            games = GameData.findByColumnAndValue(column, value, gameRepository.findAll());
            model.addAttribute("title", "Games with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("games", games);


        return "list-games";
    }
}
