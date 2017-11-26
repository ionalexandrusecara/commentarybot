package CommentaryBuilder;

import java.util.ArrayList;

/**
 * Created by Saad Moussadek on 25/11/2017.
 */
public class MatchData {
    ArrayList<Boolean> gameScoreHistory = new ArrayList();
    GameScore gameScore;
    String playerOnDisplay;
    String time_hours;
    String time_minutes;

    public MatchData(GameScore gameScore) {
        this.gameScore = gameScore;
    }

    public void updateDataSet(boolean player1ScoredLast, String time_hours, String time_minutes, String playerOnDisplay) {
        gameScoreHistory.add(player1ScoredLast);
        this.time_hours = time_hours;
        this.time_minutes = time_minutes;
        this.playerOnDisplay = playerOnDisplay;
    }

    public String getTime_hours() {
        return time_hours;
    }

    public String getTime_minutes() {
        return time_minutes;
    }

    // GAME TREND FORMAT :::
    // -1 == Player1 lost, Player2 won
    // +1 == Player1 won, Player2 lost
    // 0 == Even
    // 0.5 == Player1 has the edge
    // -0.5 == Player2 has the edge
    public double getGameTrend() {
        int player1Scored = 0;
        int player2Scored = 0;
        for (int i = 0; i < gameScoreHistory.size(); i++) {
            if(gameScoreHistory.get(i)) player1Scored++;
            else player2Scored++;
        }
        return player1Scored/5 - player2Scored/5;
    }


}
