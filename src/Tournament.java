import java.util.ArrayList;

public class Tournament {
    String tournamentName;
    Match match;
    Data data = new Data();
    UI ui;
    ArrayList<Match> matches = new ArrayList<>();
    ArrayList<Team> teames = new ArrayList<>();
    ArrayList<Team> currentTeams = new ArrayList<>();
    public Tournament(String tournamentName, ArrayList<Match> matches, ArrayList<Team> teams, ArrayList<Team> currentTeams)
    {
        this.tournamentName = tournamentName;
        this.matches = new ArrayList<>();
        this.teames = new ArrayList<>();
        this.currentTeams = new ArrayList<>();
    }

    public Tournament(){

    }




    @Override
    public String toString() {
        return tournamentName;
    }



}

