import java.util.ArrayList;

public class Tournament {
    String tournamentName;
    Match match;
    Controller data = new Controller();
    UI ui;
    ArrayList<Match> matches = new ArrayList<>();
    ArrayList<Team> teames = new ArrayList<>();
    ArrayList<Team> currentTeams = new ArrayList<>();
    private int id;


    public Tournament(String tournamentName, ArrayList<Match> matches, ArrayList<Team> teams, ArrayList<Team> currentTeams)
    {
        this.tournamentName = tournamentName;
        this.matches = new ArrayList<>();
        this.teames = new ArrayList<>();
        this.currentTeams = new ArrayList<>();
    }

    public Tournament(String tournamentName,int id){this.tournamentName = tournamentName; this.id = id;}

    public Tournament()
    {

    }

    public String getTournamentName()
    {
        return tournamentName;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return tournamentName;
    }



}

