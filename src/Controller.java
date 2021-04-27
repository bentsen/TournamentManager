import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Controller
{
    Scanner scan = new Scanner(System.in);
    Tournament tournament;
    public boolean tournamentCreated = false;


    public void registerTeam() //Skal laves om så den gemmer hold der er i DataBase(Har vi ikke lært ordenligt endnu)
    {

        System.out.println("Write your team name");
        System.out.print("\nUserInput: ");
        String teamName = scan.nextLine();
        Boolean knockout = false;
        String tournamentType = "";

        try
        {
            Team team = new Team(teamName,false,Main.tourChoose+1,1);
            Main.DBTeams.add(team);
            Main.DBAllTeams.add(team);
            Writer output;
            if(Main.tourChoose > 0)
            {
                output = new BufferedWriter(new FileWriter("src/Teams/Teams" + Main.tourChoose + ".txt", true));

                output.append("\n");
                output.append(teamName + ":" + knockout);
                output.close();
            }
            else if(tournamentCreated == true)
            {
                int index = Main.DBTournament.indexOf(tournament);
                output = new BufferedWriter(new FileWriter("src/Teams/Teams" + (index+1) + ".txt", true ));
                output.append("\n");
                output.append(teamName + ":" + knockout);
                output.close();
            }

            else
            {
                System.out.println("To register Team you have to make a tournament");
            }
        }
        catch (IOException e)
        {
            System.out.println("Something went wrong");
        }
        System.out.println("Welcome to the tournament " + teamName + " Your team has now been registered");


    }

    public void deleteTeam()     //Virker ikke skal først finde ud af hvordan man sletter noget fra DataBasen
    {
        //Removes team from arraylist
        //todo make it remove from txt file too
        Scanner sc = new Scanner(System.in);
        System.out.println(Main.teams);
        System.out.println("Type name of team to remove");
        String teamName = sc.nextLine();

        Iterator i = Main.teams.iterator();
        Team tm;
        while(i.hasNext())
        {
            tm = (Team) i.next();
            if (tm.toString().equals(teamName))
            {
                i.remove();
                System.out.println("\nThe team " +teamName+ " was removed");
                break;
            }
        }
    }

    public void randomMatchUps(ArrayList<Team> randomTeams, ArrayList<Match> randomMatch) // creates random match-ups of the teams
            // VIRKER IKKE KAN FØRST VIRKE NÅR JEG KAN SRKIVE TIL SERVEREN
    {
        ArrayList<Team> teamsList = new ArrayList<>();
        teamsList = randomTeams;
        Collections.shuffle(randomTeams);
        for (int i = 0; i < teamsList.size()-1; i+=2)
        {
            Team team1;
            Team team2;
            team1 = teamsList.get(i);
            team2 = teamsList.get(i+1);
            //create new matches with the ID of tourChoose which is the tournament that was choosen
            Match match = new Match(team1,team2,Main.tourChoose+1,0,0,true);
            randomMatch.add(match);
            Match matchCopy = new Match(team1.getTeamName(),team2.getTeamName(),Main.tourChoose+1,0,0,true);
            Main.DBAllMatches.add(matchCopy);
            //her skal der laves så man gemmer ny nye kampe der er blevet lavet.
            System.out.println("A match between " + team1 + " and " + team2 + " has now been created");
        }

    }

    public void allTeams() //er opdateret og virker :)
    {
        System.out.println("\nEnrolled teams:");
        for (Team c : Main.DBTeams)
        {
            System.out.println("-"+c);
        }

        return;
    }

    public void teamExecute(ArrayList<Match> matches) //MANGLER
    {
        int i = 0;
        while(matches.size() > i)
        {
            Match match = matches.get(i);
            //set teams that lose to have boolean true knockOut
            if (match.getTeam1Goals() > match.getTeam2Goals()) {
                match.getTeam2().setKnockedOut(true);
            } else if (match.getTeam1Goals() < match.getTeam2Goals()) {
                match.getTeam1().setKnockedOut(true);

            }
            //Removes teams that lose a knockOut game
            if (match.getTeam1().isKnockedOut() == true) {
                Main.currentTeams.remove(match.getTeam1());
            } else if (match.getTeam2().isKnockedOut() == true) {
                Main.currentTeams.remove(match.getTeam2());
            }
            //set the matches that just got simulated to false in active
            match.setActive(false);
            i++;

        }
        //removes the matches from currentMatches
        for(int p = 0; p < Main.currentMatches.size(); p++)
        {
            Main.currentMatches.remove(p);
        }

        if (Main.currentTeams.size() > 1)
        {
            System.out.println("Qualified teams: " + Main.currentTeams + "\n");
            //Main.saveCurrentTeamData();

        } else if (Main.currentTeams.size() <= 1)
        {
            System.out.println("\n" + "The winner of the tournament is " + Main.currentTeams + "\n");
        }

    }
    public void message(){
        if (Main.teams.size() >= 16){
            System.out.println("GruppeSpil");
        }else if(Main.teams.size() == 8){
            System.out.println("Quarterfinals");
        }else if(Main.teams.size() == 4){
            System.out.println("Semifinals");
        }else if(Main.teams.size() == 2){
            System.out.println("Finals");
        }
    }



    public void registerMatches(ArrayList<Match> matchList)
    {
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Match goal register");
            for (int i = 0; i < matchList.size(); i++) {
                System.out.println("Match "+(i + 1)  + "" + matchList.get(i));

            }

            System.out.println("\nChoose a Match to register goals");
            System.out.print("\nUserInput: ");
            int input = scan.nextInt();
            Match match = matchList.get(input - 1);

            System.out.println(" Match " + input);

            System.out.println("Set goals for team 1 " + match.getTeam1());
            System.out.print("\nUserInput: ");
            int input2 = scan.nextInt();
            match.setTeam1Goals(input2);

            System.out.println("Set goals for team 2 " + match.getTeam2());
            System.out.print("\nUserInput: ");
            int input3 = scan.nextInt();
            match.setTeam2Goals(input3);

            if(input3 == input2)
            {
                System.out.println("A match can not be tie!");
                match.setTeam1Goals(0);
                match.setTeam2Goals(0);
                System.out.println("Match have been reset to 0-0 score");
            }

            System.out.println("\ndo you want to register again y/n");
            System.out.print("\nUserInput: ");
            char input4 = scan.next().charAt(0);
            if(input4 == 'y')
            {
                System.out.println("Register a match again\n");
            }
            else if (input4 == 'n')
            {
                return;
            }
            else
            {
                System.out.println("That is not a valid option");
                break;
            }
        }
    }

    public void createTournament() //VIRKER IKKE KAN FØRST VIRKE NÅR JEG KAN SRKIVE TIL SERVEREN
    {
        if(Main.tourChoose > 0) {
            if (Main.tournaments.size() < 6) {
                System.out.println("Type tournament name");
                System.out.print("\nUserInput: ");
                String tournamentName = scan.nextLine();
                tournament = new Tournament(tournamentName, Main.matches, Main.teams, Main.currentTeams);
                Main.tournaments.add(tournament);
                System.out.println("Tournament " + tournamentName + " has now been created");
                tournamentCreated = true;

                try
                {
                     Writer output;
                    output = new BufferedWriter(new FileWriter("src/Tournaments/Tournaments.txt", true));
                    output.append("\n");
                    output.append(tournamentName);
                    output.close();

                }
                catch (IOException e)
                {
                    System.out.println("Something went wrong :(");
                }
            }
            else
                {
                System.out.println("You have too many tournaments saved");
                return;
            }
        }
        else
        {
            System.out.println("You cannot create a tournament while being in a tournament");
            return;
        }


    }

    public void deleteTournament() //VIRKER IKKE KAN FØRST VIRKE NÅR JEG VED HVORDAN MAN SLETTER DATA FRA SERVEREN HERFRA
    {
        int i = 0;
        System.out.println("\nTOURNAMENTS:\n");
        for(Tournament t : Main.tournaments)
        {

            System.out.println((i + 1) + "." + t + "\n");
            i++;
        }
            System.out.println("\n" + "Choose tournament to delete");
            System.out.print("\nUserInput: ");
            int tourDelete = scan.nextInt();
            System.out.println("\nConfirm delete, type " + Main.tournaments.get(tourDelete -1) + " or q for retreat");
            System.out.print("\nUserInput: ");
            Scanner confirm = new Scanner(System.in);
            String confirmDelete = confirm.nextLine();


            if(confirmDelete.equals(Main.tournaments.get(tourDelete - 1).tournamentName))
            {
                String tournamentName = Main.tournaments.get(tourDelete-1).getTournamentName();
                //Delete tournament from arraylist
                Main.tournaments.remove((tourDelete -1));
                System.out.println( "Tournament "+ tournamentName + " has now been deleted");

                //Delete tournament from txt file
                try
                {
                    File inputFile = new File("src/Tournaments/Tournaments.txt");
                    File tempFile = new File("src/Tournaments/tempTournaments.txt");

                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                    int lineToRemove = tourDelete;
                    String currentLine;
                    int count = 0;

                    while ((currentLine = reader.readLine()) != null)
                    {
                        count++;
                        if(count == lineToRemove)
                        {
                            continue;
                        }
                        writer.write(currentLine + System.getProperty("line.separator"));
                    }
                    writer.close();
                    reader.close();
                    inputFile.delete();
                    tempFile.renameTo(inputFile);

                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }

                //Delete match txt to that tournament
                try
                {
                FileWriter writer = new FileWriter("src/Matches/match" + tourDelete + ".txt",false);
                PrintWriter writer2 = new PrintWriter(writer,false);
                writer2.flush();
                writer2.close();
                writer.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                //Delete team txt to that tournament
                try
                {
                    FileWriter writer = new FileWriter("src/Teams/Teams" + tourDelete +".txt");
                    PrintWriter writer2 = new PrintWriter(writer,false);
                    writer2.flush();
                    writer2.close();
                    writer.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else if(confirmDelete.equals("q"))
            {

            }
            else
            {
                System.out.println("Wrong typing");
            }



    }

    public void saveMatchData() {

        FileWriter writer = null;
        try {
            if(Main.tourChoose > 0)
            {
                writer = new FileWriter("src/Matches/match"+Main.tourChoose+".txt");
               // writer.write(Main.getMatchDataFromSession());
            }
            else if(tournamentCreated == true)
            {
                int index = Main.tournaments.indexOf(tournament);
                writer = new FileWriter("src/Matches/match"+(index+1)+".txt");
                //writer.write(Main.getMatchDataFromSession());
            }
            else
            {
                System.out.println("To register Team you have to make a tournament");
            }
        } catch (IOException e) {
            System.out.println("Couldn't instantiate the FileWriter in saveMatchData()");
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (NullPointerException | IOException e) {
                System.out.println("Couldn't close the FileWriter in saveMatchData()");
                e.printStackTrace();
            }
        }
    }

    public void tournamentSchedule() //VIRKER MEN KAN FORBEDRES
    {
        System.out.println("\nPreviously played matches");
        for(int i = 0; i < Main.DBMatches.size(); i++) {
            if (Main.DBMatches.get(i).getTeam1Goals() > 0 || Main.DBMatches.get(i).getTeam2Goals() > 0) {
                System.out.println("Match " + (i + 1) + " " + Main.DBMatches.get(i).getTeam1() + " vs " +
                        Main.DBMatches.get(i).getTeam2() + " " + Main.DBMatches.get(i).getTeam1Goals() + "-" +
                        Main.DBMatches.get(i).getTeam2Goals());
            }

        }
        System.out.println("\nOngoing Matches:");
        for(int j = 0; j < Main.DBMatches.size(); j++) {
            if (Main.DBMatches.get(j).getTeam1Goals() == 0 && Main.DBMatches.get(j).getTeam2Goals() == 0)
            {

                System.out.println("Match " + (j + 1) + " " + Main.DBMatches.get(j).getTeam1() + " vs " +
                        Main.DBMatches.get(j).getTeam2() + " " + Main.DBMatches.get(j).getTeam1Goals() + "-" +
                        Main.DBMatches.get(j).getTeam2Goals());
            }
        }

    }

    public void tournamentSim()
    {
        message();

        if(Main.currentTeams.size() == 16) {
            Main.currentTeams = Main.DBTeams;
            teamExecute(Main.DBMatches);
        }
        else if(Main.currentTeams.size() == 8) {
            teamExecute(Main.DBMatches);
        }
        else if(Main.currentTeams.size() == 4) {
            teamExecute(Main.semifinals);
        }
        else if(Main.currentTeams.size() == 2) {
            teamExecute(Main.Finals);
        }

    }

}
