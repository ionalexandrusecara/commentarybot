package CommentaryBuilder;

/**
 * Created by Saad Moussadek on 25/11/2017.
 */
public class Venue {
    String location;
    String competition;
    String competitionStage;
    String prestige;

    public Venue(String location, String competition, String competitionStage, String prestige) {
        this.location = location;
        this.competition = competition;
        this.competitionStage = competitionStage;
        this.prestige = prestige;
    }
}
