import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.cert.PKIXBuilderParameters;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static  UI ui;
    public static ArrayList<Team> teams = new ArrayList<>();
    public static ArrayList<Match> matches = new ArrayList<>();
    public static ArrayList<Team> currentTeams = new ArrayList<>();
    public static ArrayList<Match> quarterFinals = new ArrayList<>();
    public static ArrayList<Match> Finals = new ArrayList<>();
    public static ArrayList<Match> semifinals = new ArrayList<>();
    public static ArrayList<Tournament> tournaments = new ArrayList<>();
    public static int tourChoose = 0;
    public static void main(String[] args){

        tournaments = readTournamentData("src/Tournaments/Tournaments.txt");
        continueTournament();
        ui = new UI();
        ui.mainInterface();
        Main.matches.get(0).getTeam1Goals();


    }

    public static void continueTournament() {
        if (teams.size() == 0) {
            promptNewTour();
        }
    }

    public static void promptNewTour()
    {
        Scanner tourInput = new Scanner(System.in);
        System.out.println("\nSaved Tournaments: \n");
        int i = 0;
        for (Tournament t : tournaments) {
            System.out.println(" "+(i + 1) + "." + t + "\n");
            i++;
        }
        System.out.println("Create new Tournament:\n");
        System.out.println(" "+ (i+1) + ".Create new tournament\n");
        System.out.print("UserInput: ");
        int input = tourInput.nextInt();

        if (input <= tournaments.size())
        {
            if(tournaments.size() > 0)
            {

                tourChoose = input -1;

                if (tourChoose == 0 && tournaments.size() >= 1)
                {
                    matches = readMatchData("src/Matches/match.txt");
                    teams = readTeamData("src/Teams/Teams1.txt");
                    copyTeamToCurrent();
                    tournaments = readTournamentData("src/Tournaments/Tournaments.txt");
                }
                else if (tourChoose == 1 && tournaments.size() >= 2)
                {
                    matches = readMatchData("src/Matches/match2.txt");
                    teams = readTeamData("src/Teams/Teams2.txt");
                    copyTeamToCurrent();
                    tournaments = readTournamentData("src/Tournaments/Tournaments.txt");
                }
                else if (tourChoose == 2 && tournaments.size() >= 3)
                {
                    matches = readMatchData("src/Matches/match3.txt");
                    teams = readTeamData("src/Teams/Teams3.txt");
                    copyTeamToCurrent();
                    tournaments = readTournamentData("src/Tournaments/Tournaments.txt");
                }
                else if (tourChoose == 3 && tournaments.size() >= 4)
                {
                    matches = readMatchData("src/Matches/match4.txt");
                    teams = readTeamData("src/Teams/Teams4.txt");
                    copyTeamToCurrent();
                    tournaments = readTournamentData("src/Tournaments/Tournaments.txt");
                }
                else if (tourChoose == 4 && tournaments.size() >= 5)
                {
                    matches = readMatchData("src/Matches/match5.txt");
                    teams = readTeamData("src/Teams/Teams5.txt");
                    copyTeamToCurrent();
                    tournaments = readTournamentData("src/Tournaments/Tournaments.txt");
                }
                else
                    {
                    System.out.println("That is not a valid option");
                }
            }

        }
        else if(input == i+1)
        {
            Data data = new Data();
            data.createTournament();
        }
        else
        {
            System.out.println("That is not a valid option");
            return;
        }

    }

    public static void getCurrentTeams(String getmatchtxt)
    {
        if(currentTeams.size() == 0)
        {
            matches = readMatchData("src/Matches/match"+ getmatchtxt + ".txt");
        }
        else if(currentTeams.size() == 8)
        {
            quarterFinals = readMatchData("src/Matches/match" + getmatchtxt + ".txt");
        }
        else if(currentTeams.size() == 4)
        {
            semifinals =readMatchData("src/Matches/match" + getmatchtxt + ".txt");
        }
        else if(currentTeams.size() == 2)
        {
            Finals = readMatchData("src/Matches/match" + getmatchtxt + ".txt");
        }
    }

    public static void copyTeamToCurrent()
    {
        for(int i = 0; i < teams.size(); i++)
        {
            if (teams.get(i).knockedOut = false)
            {
                currentTeams.add(teams.get(i));
            }
        }
    }


    public static String getUserInput(String msg){
        System.out.print(msg);
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static ArrayList<Team> readTeamData(String file) {
        ArrayList<Team> teamList = new ArrayList<>();
        Scanner scanner = null;
        File fr = new File(file);
        try {

            scanner = new Scanner(fr);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner != null)
        {
            while(scanner.hasNextLine())
            {
                String[] colonSeperatedValues = scanner.nextLine().split(":");
                String teamName = colonSeperatedValues[0];
                boolean knockedOut = Boolean.parseBoolean(colonSeperatedValues[1]);
                teamList.add(new Team(teamName,knockedOut));
            }
        }

        return teamList;
    }

    public static String getGameDataFromSession()
    {
        StringBuilder gameData = new StringBuilder();
        for (Team t : Main.currentTeams)
        {
            String teamData = String.format(t.getTeamName()+"\n");
            gameData.append(teamData);
        }
        return gameData.toString();
    }

    public static String getMatchDataFromSession()
    {
        StringBuilder gameData = new StringBuilder();
        for (Match m : Main.matches)
        {
            String matchData = String.format(m.getTeam1() + ":" + m.getTeam2() +":"+m.getTeam1Goals()+":"+m.getTeam2Goals()+"\n");
            gameData.append(matchData);
        }
        return gameData.toString();
    }

    public static void saveCurrentTeamData() {
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/CurrentTeams.txt");
            writer.write(getGameDataFromSession());
        } catch (IOException e) {
            System.out.println("Couldn't instantiate the FileWriter in saveCurrentTeamData()");
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (NullPointerException | IOException e) {
                System.out.println("Couldn't close the FileWriter in saveCurrentTeamData()");
                e.printStackTrace();
            }
        }
    }


    public static ArrayList<Match> readMatchData(String file) {
        ArrayList<Match> matchList = new ArrayList<>();
        Scanner scanner = null;
        File fr = new File(file);
        try {

            scanner = new Scanner(fr);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner != null) {
            while (scanner.hasNextLine()) {
                Team team1 = new Team("teamName",false);
                Team team2 = new Team("teamName",false);

                String[] colonSeperatedValues = scanner.nextLine().split(":");
                team1.setTeamName(colonSeperatedValues[0]);
                team2.setTeamName(colonSeperatedValues[1]);

                int i =0;
                while(i < currentTeams.size()) {
                    if (currentTeams.get(i).equals(team1.getTeamName()))
                    {
                        team1 = currentTeams.get(i);
                    }
                    if (currentTeams.get(i).equals(team2.getTeamName()))
                    {
                        team2 = currentTeams.get(i);
                    }
                    i++;
                }

                int team1Goal = Integer.parseInt(colonSeperatedValues[2]);
                int team2Goal = Integer.parseInt(colonSeperatedValues[3]);
                matchList.add(new Match(team1,team2,team1Goal,team2Goal));
            }
        }

        return matchList;
    }

    public static ArrayList<Tournament> readTournamentData(String file) {
        ArrayList<Tournament> tournamentList = new ArrayList<>();
        Scanner scanner = null;
        File fr = new File(file);
        try {

            scanner = new Scanner(fr);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner != null) {
            while (scanner.hasNextLine())
            {
                   String tourName = scanner.nextLine();

                tournamentList.add(new Tournament(tourName,matches,teams,currentTeams));
            }
        }

        return tournamentList;
    }



}
