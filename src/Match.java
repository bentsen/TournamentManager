import java.util.ArrayList;
import java.util.Collections;

public class Match {
    Team team1;
    Team team2;
    String Team1Name;
    String Team2name;
    int team1Goals;
    int team2Goals;
    UI ui;
    int id;
    int tournament_id;
    boolean active = true;

    public Match(String Team1Name, String Team2name, int id ,int tournament_id, int team1Goals, int team2Goals,boolean active)
    {
        this.active = active;
        this.Team1Name = Team1Name;
        this.Team2name = Team2name;
        this.tournament_id = tournament_id;
        this.team1Goals = team1Goals;
        this.team2Goals = team2Goals;
    }

    public Match(Team Team1Name, Team Team2name, int id ,int tournament_id, int team1Goals, int team2Goals,boolean active){}



    public void setTeam1Goals(int team1Goals) {
        this.team1Goals = team1Goals;
    }

    public void setTeam2Goals(int team2Goals) {
        this.team2Goals = team2Goals;
    }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public String getTeam1Name()
    {
        return Team1Name;
    }

    public String getTeam2Name(){return Team2name;}

    public Team getTeam1(){return team1;}

    public Team getTeam2(){return team1;}

    public void setTeam1(Team team1) { this.team1 = team1; }

    public void setTeam2(Team team2) { this.team2 = team2; }

    public int getTeam1Goals()
    {
        return team1Goals;
    }

    public int getTeam2Goals()
    {
        return team2Goals;
    }

    public void setId(int id) { this.id = id; }

    public void setTournament_id(int tournament_id) { this.tournament_id = tournament_id; }

    public int getId() { return id; }

    public int getTournament_id() { return tournament_id; }





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
