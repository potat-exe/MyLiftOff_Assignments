package org.launchcode.BoardGameEmporium.models;

import java.util.ArrayList;


public class GameData {


    public static ArrayList<Game> findByColumnAndValue(String column, String value, Iterable<Game> allGames){

        ArrayList<Game> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Game>) allGames;
        }

        if (column.equals("all")){
            results = findByValue(value, allGames);
            return results;
        }
        for (Game game : allGames) {

            String aValue = getFieldValue(game, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(game);
            }
        }

        return results;
    }

    public static String getFieldValue(Game game, String fieldName){
        String theValue = "";
        if (fieldName.equals("name")){
            theValue = game.getName();
        }

        return theValue;
    }

    public static ArrayList<Game> findByValue(String value, Iterable<Game> allGames) {
        String lower_val = value.toLowerCase();

        ArrayList<Game> results = new ArrayList<>();

        for (Game game : allGames) {

            if (game.getName().toLowerCase().contains(lower_val)) {
                results.add(game);
            } else if (game.toString().toLowerCase().contains(lower_val)) {
                results.add(game);
            }

        }

        return results;
    }
}
