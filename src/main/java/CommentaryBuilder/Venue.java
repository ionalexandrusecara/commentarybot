package CommentaryBuilder;

/**
 * Created by Saad Moussadek on 25/11/2017.
 */
public class Venue {
    String location;
    String name;
    String competitionStage;
    String prestige;

    public Venue() {
    }

    public Venue(String location, String name, String competitionStage, String prestige) {
        this.location = location;
        this.name = name;
        this.competitionStage = competitionStage;
        this.prestige = prestige;
    }
}
