import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Data
{
    Scanner scan = new Scanner(System.in);
    Tournament tournament;
    public boolean tournamentCreated = false;


    public void registerTeam()
    {

        System.out.println("Write your team name");
        System.out.print("\nUserInput: ");
        String teamName = scan.nextLine();
        Boolean knockout = false;
        String tournamentType = "";

        try
        {
            Team team = new Team(teamName,false);
            Main.teams.add(team);
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
                int index = Main.tournaments.indexOf(tournament);
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

    public void deleteTeam()        //Viker ikke
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
            Match match = new Match(team1,team2, 0, 0);
            randomMatch.add(match);
            saveMatchData();
            System.out.println("A match between " + team1 + " and " + team2 + " has now been created");
        }

    }

    public void allTeams()
    {
        System.out.println("Enrolled teams ");
        for (Team c : Main.teams)
        {
            System.out.println(c);
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
            i++;

        }
        if (Main.currentTeams.size() > 1) {
            System.out.println("Qualified teams: " + Main.currentTeams + "\n");
            Main.saveCurrentTeamData();

        } else if (Main.currentTeams.size() <= 1) {
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

    public void createTournament()
    {
        if(Main.tourChoose == 0) {
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

    public void deleteTournament() //mangler stadig at slette hold fra txt filerne
    {
        int i = 0;
        for(Tournament t : Main.tournaments)
        {
            System.out.println( i+1 + "tournament saved: \n");
            System.out.println(t + "\n");
            System.out.println("\n" + "Choose tournament to delete");
            System.out.println("\nUserInput: ");
            int tourDelete = scan.nextInt();
            System.out.println("Confirm delete, type " + Main.tournaments.get(tourDelete -1) + " or q for retreat");
            System.out.print("\nUserInput: ");
            String confirmDelete = scan.nextLine();
            if(confirmDelete.equals(Main.tournaments.toString()))
            {
                Main.tournaments.remove(tourDelete -1);
                System.out.println( Main.tournaments.get(tourDelete -1) + " has now been deleted");
                //Delete tournament from txt file
                try {
                    File inputFile = new File("src/Tournaments/Tournaments.txt");
                    File tempFile = new File("myTempFile.txt");

                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                    String lineToRemove = confirmDelete;
                    String currentLine;

                    while ((currentLine = reader.readLine()) != null)
                    {
                        //Trims newline when comparing with lineToRemove
                        String trimmedLine = currentLine.trim();
                        if (trimmedLine.equals(lineToRemove)) continue;
                        writer.write(currentLine + System.getProperty("line.separator"));
                    }
                    writer.close();
                    writer.close();
                    inputFile.delete();
                    boolean succesful = tempFile.renameTo(inputFile);
                }
                catch (IOException e)
                {
                    System.out.println("Could not delete tournament from files");
                }
                return;
            }
            else if(confirmDelete.equals("q"))
            {
                return;
            }
            else
            {
                System.out.println("This is not a valid option");
                return;
            }

        }
    }

    public void saveMatchData() {

        FileWriter writer = null;
        try {
            if(Main.tourChoose > 0)
            {
                writer = new FileWriter("src/Matches/match"+Main.tourChoose+".txt");
                writer.write(Main.getMatchDataFromSession());
            }
            else if(tournamentCreated == true)
            {
                int index = Main.tournaments.indexOf(tournament);
                writer = new FileWriter("src/Matches/match"+(index+1)+".txt");
                writer.write(Main.getMatchDataFromSession());
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

    public void tournamentSchedule()
    {
        for(int i = 0; i < Main.matches.size(); i++)
        {
            System.out.println("\nMatch " + (i+1)+ " " +Main.matches.get(i).getTeam1() + " vs " +
                    Main.matches.get(i).getTeam2() + " " + Main.matches.get(i).getTeam1Goals() + "-" +
                    Main.matches.get(i).getTeam2Goals());
        }

    }

    public void tournamentSim() {

        message();

        if(Main.currentTeams.size() == 0) {
            Main.currentTeams = Main.teams;
            teamExecute(Main.matches);
        }
        else if(Main.currentTeams.size() == 8) {
            teamExecute(Main.quarterFinals);
        }
        else if(Main.currentTeams.size() == 4) {
            teamExecute(Main.semifinals);
        }
        else if(Main.currentTeams.size() == 2) {
            teamExecute(Main.Finals);
        }

    }

}
