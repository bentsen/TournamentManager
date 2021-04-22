import javax.sql.DataSource;
import java.io.*;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static  UI ui;
    //Arraylist that properly should be deleted
    public static ArrayList<Team> teams = new ArrayList<>();
    public static ArrayList<Match> matches = new ArrayList<>();

    //ArrayList that may be used

    public static ArrayList<Tournament> tournaments = new ArrayList<>();

    //ArrayList that should be used (currentTeams is for the teams that havent been knockedOut of the tournament yet)
    public static ArrayList<Match> quarterFinals = new ArrayList<>();
    public static ArrayList<Match> Finals = new ArrayList<>();
    public static ArrayList<Match> semifinals = new ArrayList<>();

    public static ArrayList<Team> currentTeams = new ArrayList<>();
    public static ArrayList<Tournament> DBTournament = new ArrayList<>();
    public static ArrayList<Team> DBTeams = new ArrayList<>();
    public static ArrayList<Match> DBMatches = new ArrayList<>();
    public static ArrayList<Match> currentMatches = new ArrayList<>();
    public static IO io;
    public static int tourChoose = 0;



    public static void main(String[] args)
    {
        io = getIo();

        DBTournament = io.readTournamentData();
        DBTeams = io.readTeamData();
        DBMatches = io.readMatchData();
        promptNewTour();
        ui = new UI();
        ui.mainInterface();

    }

    public static IO getIo()
    {
        return new DBConnector();
    }



    public static void promptNewTour()
    {
            //Double for loop to place Team in the right Matches
            for(int p = 0; p < DBMatches.size(); p++)
            {
                for(int h = 0; h < DBTeams.size(); h++)
                {
                    if (DBTeams.get(h).teamName.equals(DBMatches.get(p).Team1Name))
                    {
                        DBMatches.get(p).setTeam1(DBTeams.get(h));
                    }
                    else if (DBTeams.get(h).teamName.equals(DBMatches.get(p).Team2name))
                    {
                        DBMatches.get(p).setTeam2(DBTeams.get(h));
                    }
                }
            }

        Scanner tourInput = new Scanner(System.in);
        System.out.println("\nSaved Tournaments: \n");
        int i = 0;
        for(Tournament t: DBTournament)
        {
            System.out.println(" "+(i + 1) + "." + t + "\n");
            i++;
        }
        System.out.println("Create new Tournament:\n");
        System.out.println(" "+ (i+1) + ".Create new tournament\n");
        System.out.print("UserInput: ");
        int input = tourInput.nextInt();

        if (input <= DBTournament.size())
        {
                //tourChoose will safe the number of the tournament the user selected and is converted to arraylist format
                tourChoose = input -1;

                    //Check if teams has the selected tournaments ID
                    for(int q = 0; q < DBTeams.size(); q++)
                    {
                        if(DBTeams.get(q).getTournament_id() != DBTournament.get(tourChoose).getId())
                        {
                            DBTeams.remove(q);
                            q--;
                        }
                        else if(DBTeams.get(q).getTournament_id() == DBTournament.get(tourChoose).getId())
                        {
                            //if team has the selected tournament ID and is not knockout of tournament.
                            if(DBTeams.get(q).isKnockedOut() == false)
                            {
                                currentTeams.add(DBTeams.get(q));
                            }
                        }
                    }
                    //Check if matches has the selected tournaments ID
                    for(int j = 0; j < DBMatches.size(); j++)
                    {
                        if (DBMatches.get(j).getTournament_id() != DBTournament.get(tourChoose).getId())
                        {
                            DBMatches.remove(j);
                            j--;
                        }
                        //if match has the selected tournament ID and is not active false
                        else if(DBMatches.get(j).getTournament_id() == DBTournament.get(tourChoose).getId())
                        {
                            if(DBMatches.get(j).isActive() == true)
                            {
                                currentMatches.add(DBMatches.get(j));
                            }
                        }
                    }

                    //thoose for each loops to print out teams and matches is just for safety ATM should be deleted at some time
                    System.out.println("\nMatches in this tournament:");
                    for(Match m : DBMatches)
                    {
                        System.out.println(m);
                    }
                    System.out.println("\nTeams in this tournament:");
                    for(Team t : DBTeams)
                    {
                        System.out.println(t);
                    }
                     System.out.println("\nteams that is in currentTeams(Teams who has not been knockOut)");
                    for(Team g : currentTeams)
                    {
                        System.out.println(g);
                    }

        }
        else if(input == i+1)
        {
            tourChoose = input -1;
            Controller data = new Controller();
            data.createTournament();
        }
        else
        {
            System.out.println("That is not a valid option");
            return;
        }
    }

}
