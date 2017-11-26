package CommentaryBuilder;

/**
 * Created by Saad Moussadek on 25/11/2017.
 */
public class GameScore {
    //TO COMPLETE
    String[] currentGameScores = new String[2];
    String[] firstSets = new String[2];
    String[] thirdSet = new String[2];

    public GameScore(String player1set, String player2set, String player1set3, String player2set3, String player1score, String player2score) {
        currentGameScores[0] = player1score;
        currentGameScores[1] = player2score;
        firstSets[0] = player1set;
        firstSets[1] = player2set;
        thirdSet[0] = player1set3;
        thirdSet[1] = player2set3;
    }

    //TO COMPLETE
    @Override
    public String toString() {
        String output = "";

        if (currentGameScores[0].equals("") || currentGameScores[1].equals("") || firstSets[0].equals("") || firstSets[1].equals("")
                || thirdSet[0].equals("") || thirdSet[1].equals("")) {
            return "ERROR";
        }
        if (firstSets[0].equals(1) && firstSets[1].equals(1)) {
            output += "Sets even.";
        }
        else if (firstSets[0].equals(0) && firstSets[1].equals(1)) {
            output += "0 to 1 set.";
        }
        else if (firstSets[0].equals(1) && firstSets[1].equals(0)) {
            output += "1 to 0 set.";
        }
        else if (firstSets[0].equals(1) && firstSets[1].equals(1)) {
            output += "Zero zero sets.";
        }
        if (thirdSet[0].equals(thirdSet[1]) && !thirdSet[0].equals("0")) {
            output += "Players are evenly matched as both of them have won " + thirdSet[0] + " games in this third set.";
        }
        else {
            output += thirdSet[0] + " games to " + thirdSet[1] + " in this third set for now.";
        }
        output += currentGameScores[0] + " " + currentGameScores[1] + ".";
        System.out.println("OUTPUT IS " + output);
        return output;
    }

}
