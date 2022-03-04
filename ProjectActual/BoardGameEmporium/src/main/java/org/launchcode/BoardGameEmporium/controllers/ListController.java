package org.launchcode.BoardGameEmporium.controllers;


import org.launchcode.BoardGameEmporium.models.Game;
import org.launchcode.BoardGameEmporium.models.GameData;
import org.launchcode.BoardGameEmporium.models.data.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.util.HashMap;

@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    private GameRepository gameRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("all","All");
        columnChoices.put("dice","Dice");
        columnChoices.put("board","Board");
        columnChoices.put("card","Card");

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
        }else if(column.toLowerCase().equals("card")){
            games = GameData.findByGameType(column, value, gameRepository.findAll());
            model.addAttribute("title", "Games with " + columnChoices.get(column) );
        }else if(column.toLowerCase().equals("board")){
            games = GameData.findByGameType(column, value, gameRepository.findAll());
            model.addAttribute("title", "Games with " + columnChoices.get(column) );
        }else if(column.toLowerCase().equals("dice")){
            games = GameData.findByGameType(column, value, gameRepository.findAll());
            model.addAttribute("title", "Games with " + columnChoices.get(column) );
        }else {
            games = GameData.findByColumnAndValue(column, value, gameRepository.findAll());
            model.addAttribute("title", "Games with " + columnChoices.get(column) );
        }
        model.addAttribute("games", games);


        return "list-games";
    }

//    @RequestMapping(value = "type")
//    public String listGamesByType(Model model, @RequestParam String column, @RequestParam String value) {
//        Iterable<Game> games;
//
//            games = GameData.findByGameType(column, value, gameRepository.findAll());
//            model.addAttribute("title", "Games with " + columnChoices.get(column) + ": " + value);
//
//
//
//        model.addAttribute("games", games);
//
//
//        return "list-card";
//    }
}
