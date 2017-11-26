package CommentaryBuilder;

import java.util.ArrayList;

/**
 * Created by Saad Moussadek on 25/11/2017.
 */
public class PlayerData {
    //REMOVE SAMPLE VALUES AFTERWARDS
    String name = "test";
    int worldRank = 7;
    Venue[] championshipsWon = {new Venue()};
    ArrayList<String> moodHistory = new ArrayList<String>();

    public PlayerData(String name) {
        this.name = name;
    }

    public void updateDataSet(String mood) {
        if (! mood.equals(""))
            moodHistory.add(mood);
        else
            moodHistory.add("neutral");
    }

    public String getCurrentMood() {
        if (moodHistory.size() != 0)
            return moodHistory.get(moodHistory.size() - 1);
        else throw new ArrayIndexOutOfBoundsException();
    }

    // MOOD TREND FORMAT :::
    // -1 == Player cries
    // +1 == Player is happy
    // 0 == Player is neutral
    // 0.5 == Player is showing more and more character
    // -0.5 == Player is more and more desperate
    public double getMoodTrend() {
        return 0;
    }

}
