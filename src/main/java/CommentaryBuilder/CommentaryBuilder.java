package CommentaryBuilder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Saad Moussadek on 25/11/2017.
 */
public class CommentaryBuilder {
    //FREQUENCIES ::::: gameScoreCommentary, gameTimeCommentary, playerCommentary, moodCommentary, gameShape, venue
    double[] frequencySet = {0.6, 0.2, 0.75, 0.3, 0.4, 0.1};
    MatchData matchData;
    PlayerData player1;
    PlayerData player2;
    GameScore gameScore;
    Venue venue;

    public CommentaryBuilder(String player1Name, String player2Name, GameScore gameScore, Venue venue) {
        setupMatchData();
        setupPlayers(player1Name, player2Name);
        this.gameScore = gameScore;
        this.venue = venue;
    }

    private void setupPlayers(String player1Name, String player2Name) {
        player1 = new PlayerData(player1Name);
        player2 = new PlayerData(player2Name);
    }

    private void setupMatchData() {
        matchData = new MatchData(gameScore);
    }

    private PlayerData getPlayerOnDisplay(String playerOnDisplay) {
        if (playerOnDisplay.equals(player1.name))
            return player1;
        else if (playerOnDisplay.equals(player2.name))
            return player2;
        else
            return null;
    }


    public void updateDataSets(GameScore gameScore, String time_hours, String time_minutes, String playerOnDisplay, String mood) {
        //CARE HERE
        matchData.updateDataSet(false, time_hours, time_minutes, playerOnDisplay);
        if (playerOnDisplay.equals(player1.name)) {
            player1.updateDataSet(mood);
            return;
        }
        else if (playerOnDisplay.equals(player2.name)) {
            player2.updateDataSet(mood);
            return;
        }
    }








    //COMMENTARIES ::::::::
    //_________________________________________________________________________________

    public String makeCommentary() {
        String finalCommentary = "";
        HashMap<String, String> commentaries = new HashMap<String, String>();
        if (Math.random() < 0.3)
            commentaries.put("filler", makeFillerCommentary());
        if (Math.random() < frequencySet[0])
            commentaries.put("gameScore", makeGameScoreCommentary(gameScore.toString()));
        if (Math.random() < frequencySet[1])
            commentaries.put("gameTime", makeGameTimeCommentary(matchData.getTime_hours(), matchData.getTime_minutes()));
        if (Math.random() < frequencySet[2])
            commentaries.put("player", makePlayerCommentary(matchData.playerOnDisplay));
        if (Math.random() < frequencySet[3])
            commentaries.put("mood", makeMoodCommentary(getPlayerOnDisplay(matchData.playerOnDisplay), getPlayerOnDisplay(matchData.playerOnDisplay).getCurrentMood()));
        if (Math.random() < frequencySet[4])
            commentaries.put("gameShape", makeGameTrendCommentary(matchData.getGameTrend()));
        if (Math.random() < frequencySet[5])
            commentaries.put("venue", makeVenueCommentary(venue));

        //TO IMPROVE (grammar)
        for (String key: commentaries.keySet()) {
            if (commentaries.get(key) != null && !commentaries.get(key).equals("")) {
                finalCommentary += commentaries.get(key) + "\n";
            }
        }

        return finalCommentary;
    }

    //_________________________________________________________________________________

    private String makeFillerCommentary() {
        ArrayList<String> possibleCommentaries = new ArrayList();

        possibleCommentaries.add("Well played!");
        possibleCommentaries.add("Wonderful play!");
        possibleCommentaries.add("Great point!");
        possibleCommentaries.add("Breathtaking!");
        possibleCommentaries.add("Can't take my eyes of the ball! Amazing!");

        int chosenCommentary = (int) Math.floor(Math.random() * possibleCommentaries.size());
        return possibleCommentaries.get(chosenCommentary);
    }

    private String makeGameScoreCommentary(String gameScore) {
        if(gameScore.equals("ERROR"))
            return "";
        ArrayList<String> possibleCommentaries = new ArrayList();
        possibleCommentaries.add(gameScore + ".");
        possibleCommentaries.add("Some really great points! Both players are putting in a great effort!");
        possibleCommentaries.add("So much effort from both players! They are making a great display!");
        possibleCommentaries.add("You can feel the pressure in the air! Both players want to win!");


        int chosenCommentary = (int) Math.floor(Math.random() * possibleCommentaries.size());
        return possibleCommentaries.get(chosenCommentary);
    }

    private String makeGameTimeCommentary(String time_hours, String time_minutes) {
        ArrayList<String> possibleCommentaries = new ArrayList();
        if (time_hours.contains("ERROR") || time_minutes.contains("ERROR"))
            return "Difficult to get tired of this game despite it's length!";
        possibleCommentaries.add("We are now " + time_hours + " hours and " + time_minutes + " minutes into today's game.");
        possibleCommentaries.add(time_hours + " hours, " + time_minutes + " minutes already into this game.");
        possibleCommentaries.add("So many things happened already during the course of the last " + time_hours + " hours and " + time_minutes + " minutes.");

        int chosenCommentary = (int) Math.floor(Math.random() * possibleCommentaries.size());
        return possibleCommentaries.get(chosenCommentary);
    }

    private String makePlayerCommentary(String playerOnDisplay) {
        ArrayList<String> possibleCommentaries = new ArrayList();

        possibleCommentaries.add(playerOnDisplay + ", on display. Big game for this player today.");
        possibleCommentaries.add("Ranked " + getPlayerOnDisplay(playerOnDisplay).worldRank + ", " + playerOnDisplay + " is looking for more in this game.");
        possibleCommentaries.add("Can " + playerOnDisplay + " improve on her form, currently ranked as number " + getPlayerOnDisplay(playerOnDisplay).worldRank + " worldwide?");
        possibleCommentaries.add(playerOnDisplay + ", shows great composure! She is slowly improving on her mistakes");
        possibleCommentaries.add(playerOnDisplay + ", is putting in a good shift! No wonder she got this high in the rankings!");

        int chosenCommentary = (int) Math.floor(Math.random() * possibleCommentaries.size());
        return possibleCommentaries.get(chosenCommentary);
    }

    private String makeMoodCommentary(PlayerData playerOnDisplay, String mood) {
        ArrayList<String> possibleCommentaries = new ArrayList();

        possibleCommentaries.add(playerOnDisplay.name + ", showing much " + mood + " after the last play.");
        possibleCommentaries.add("You can see " + mood + " on " + playerOnDisplay.name + ". Does she have what it takes?");
        //possibleCommentaries.add();

        int chosenCommentary = (int) Math.floor(Math.random() * possibleCommentaries.size());
        return possibleCommentaries.get(chosenCommentary);
    }

    private String makeGameTrendCommentary(double gameTrend) {
        ArrayList<String> possibleCommentaries = new ArrayList();

        if (gameTrend < -0.5) {
            possibleCommentaries.add("Things are not really going " + player1.name + "'s way right now.");
            possibleCommentaries.add(player1.name + "has to change her game. It's really not looking good.");
            possibleCommentaries.add("Call 911! " + player2.name + " is on fire!");
            possibleCommentaries.add("This is " + player2.name + " at her best!");
            possibleCommentaries.add("This is " + player2.name + " for you! Amazing effort!");
            possibleCommentaries.add("Tremendous performance from " + player2.name + " in the past few plays.");
        }
        else if (gameTrend > -0.5 && gameTrend < -0.2) {
            possibleCommentaries.add(player2.name + " starting to get a slight edge over her opponent.");
            possibleCommentaries.add(player2.name + " has the edge in this game!");
            possibleCommentaries.add(player2.name + " has the psychological advantages in this game!");
            possibleCommentaries.add(player2.name + " takes the lead in this game. Her recent form shows improvement!");
        }
        else if (gameTrend > -0.2 && gameTrend < 0.2) {
            possibleCommentaries.add("An extremely tight game currently, for both the players.");
            possibleCommentaries.add("Difficult to differentiate between two very good players right now.");
            possibleCommentaries.add("Both players are putting in a great shift! We witnessed some amazing plays today!");
            possibleCommentaries.add("Tennis is about discipline and effort. Both players are really showing that today!");
        }
        else if (gameTrend > 0.2 && gameTrend < 0.5) {
            possibleCommentaries.add(player1.name + " starting to get a slight edge over her opponent.");
            possibleCommentaries.add(player1.name + " has the edge in this game!");
            possibleCommentaries.add(player2.name + " has the psychological advantages in this game!");
            possibleCommentaries.add(player1.name + " takes the lead in this game. Her recent form shows improvement!");
        }
        else {
            possibleCommentaries.add("Things are not really going " + player2.name + "'s way right now.");
            possibleCommentaries.add("Tremendous performance from " + player1.name + " in the past few plays.");
            possibleCommentaries.add(player2.name + " has to change her game. It's really not looking good.");
            possibleCommentaries.add("Call 911!" + player1.name + " is on fire!");
            possibleCommentaries.add("This is " + player1.name + " at her best!");
            possibleCommentaries.add("This is " + player1.name + " for you! Amazing effort!");
        }
        int chosenCommentary = (int) Math.floor(Math.random() * possibleCommentaries.size());
        return possibleCommentaries.get(chosenCommentary);
    }

    private String makeVenueCommentary(Venue venue) {
        ArrayList<String> possibleCommentaries = new ArrayList();

        possibleCommentaries.add("Again a pleasure to comment on a tennis game at " + venue.name + " in " + venue.location + ".");
        possibleCommentaries.add("It's still anyone's game today, at " + venue.name + "!");
        possibleCommentaries.add("There's always such a nice atmosphere here at" + venue.name + "!");
        possibleCommentaries.add("I never get tired of coming back to " + venue.name + "! Such an amazing court!");
        //possibleCommentaries.add("");

        int chosenCommentary = (int) Math.floor(Math.random() * possibleCommentaries.size());
        return possibleCommentaries.get(chosenCommentary);
    }




}
