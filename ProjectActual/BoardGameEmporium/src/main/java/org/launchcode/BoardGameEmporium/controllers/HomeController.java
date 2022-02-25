package org.launchcode.BoardGameEmporium.controllers;

import org.launchcode.BoardGameEmporium.models.Game;
import org.launchcode.BoardGameEmporium.models.data.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping("")
    public String myHomeScreen(Model model) {

        model.addAttribute("title","My Games");

        return "index";
    }

    @GetMapping("add")
    public String displayAddGameForm(Model model){
        model.addAttribute("Game Title", "Add Game");
        model.addAttribute(new Game());
        return "add";
    }

    @PostMapping("add")
    public  String processAddGameForm(@ModelAttribute @Valid Game newGame, Errors errors,
                                      Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title","Add Game");
            return "add";
        }

        gameRepository.save(newGame);

        return "redirect:";
    }

    @GetMapping("view/{gameId}")
    public String displayViewGame(Model model, @PathVariable int gameId){

        Optional optGame = gameRepository.findById(gameId);
        if (optGame.isPresent()) {
            Game game = (Game) optGame.get();
            model.addAttribute("game", game);
            return "games/view"; }
        else{
                return "redirect:../";
            }
        }


    }

