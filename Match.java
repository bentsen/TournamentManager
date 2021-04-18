import java.util.ArrayList;
import java.util.Collections;

public class Match {
    Team team1;
    Team team2;
    int team1Goals;
    int team2Goals;
    UI ui;

    public Match(Team team1, Team team2, int team1Goals, int team2Goals)
    {
        this.team1 = team1;
        this.team2 = team2;
        this.team1Goals = team1Goals;
        this.team2Goals = team2Goals;
    }



    public void setTeam1Goals(int team1Goals) {
        this.team1Goals = team1Goals;
    }

    public void setTeam2Goals(int team2Goals) {
        this.team2Goals = team2Goals;
    }

    public Team getTeam1()
    {
        return team1;
    }

    public Team getTeam2()
    {
        return team2;
    }

    public int getTeam1Goals()
    {
        return team1Goals;
    }

    public int getTeam2Goals()
    {
        return team2Goals;
    }

    @Override
    public String toString() {
        return
                " team1: " + team1 +
                        " Goals: " + team1Goals +
                        " vs " +
                        "team2: " + team2 +
                        " Goals: " + team2Goals;
    }



}
