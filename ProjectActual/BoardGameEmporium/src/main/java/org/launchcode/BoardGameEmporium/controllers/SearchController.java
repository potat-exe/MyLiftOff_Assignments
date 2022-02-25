package org.launchcode.BoardGameEmporium.controllers;

import org.launchcode.BoardGameEmporium.models.Game;
import org.launchcode.BoardGameEmporium.models.GameData;
import org.launchcode.BoardGameEmporium.models.data.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.launchcode.BoardGameEmporium.controllers.ListController.columnChoices;


@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping("")
    public String search(Model model){
        model.addAttribute("columns",columnChoices);
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Game> games;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            games = gameRepository.findAll();
        } else {
            games = GameData.findByColumnAndValue(searchType, searchTerm, gameRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Games with : " + searchTerm);
        model.addAttribute("games", games);

        return "search";
    }
}
