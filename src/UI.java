import java.util.Scanner;

public class UI
{
    Scanner interfaceScan = new Scanner(System.in);
    int userInput;
    Controller data = new Controller();
    Tournament tour = new Tournament();

    public void mainInterface()
    {

        System.out.println("\nMAIN INTERFACE - TOURNAMENT " + Main.DBTournament.get(Main.tourChoose).getTournamentName().toUpperCase());
        System.out.println(" 1.Team menu");
        System.out.println(" 2.Match menu");
        System.out.println(" 3.Tournament menu");
        System.out.println(" 4.Exit and save");
        System.out.print("\nUserInput: ");
        userInput = interfaceScan.nextInt();

        switch (userInput)
        {
            case 1:
                teamMenu();
                break;
            case 2:
                matchMenu();
                break;
            case 3:
                tournamentMenu();
                break;

            case 4:
                Main.io.saveTeamData();
                Main.io.saveMatchData();
                System.exit(0);
            default:
                System.out.println("not a valid option");
        }

    }

    public void teamMenu()
    {
        System.out.println("\nTEAM MENU - TOURNAMENT " + Main.DBTournament.get(Main.tourChoose).getTournamentName().toUpperCase());
        System.out.println(" 1.Register team");
        System.out.println(" 2.Delete team");
        System.out.println(" 3.View all teams registered");
        System.out.println(" 4.Back to menu");
        System.out.print("\n UserInput: ");
        userInput = interfaceScan.nextInt();

        switch (userInput)
        {
            case 1:
                data.registerTeam();
                mainInterface();
                break;
            case 2:
                data.deleteTeam();
                System.out.println(Main.teams);
                mainInterface();
                break;
            case 3:
                data.allTeams();
                mainInterface();
                break;
            case 4:
                mainInterface();
                break;
            default:
                System.out.println("Not a valid option");

        }
    }

    public void matchMenu()
    {

        System.out.println("\nMATCH MENU - TOURNAMENT " + Main.DBTournament.get(Main.tourChoose).getTournamentName().toUpperCase());
        System.out.println(" 1.Create Matches");
        System.out.println(" 2.Register match results");
        System.out.println(" 3.Back to menu");
        System.out.println(" 4.read test");
        System.out.print("\nUserInput: ");
        userInput = interfaceScan.nextInt();

        switch (userInput)
        {
            case 1:
                if(Main.currentTeams.size() == 16) {
                    data.randomMatchUps(Main.DBTeams, Main.DBMatches);
                }
                else if(Main.currentTeams.size() == 8) {
                    data.randomMatchUps(Main.currentTeams, Main.currentMatches);
                }
                else if(Main.currentTeams.size() == 4) {
                    data.randomMatchUps(Main.currentTeams, Main.currentMatches);
                }
                else if(Main.currentTeams.size() == 2) {
                    data.randomMatchUps(Main.currentTeams, Main.currentMatches);
                }
                mainInterface();
                break;
            case 2:
                if(Main.currentTeams.size() == 16) {
                    System.out.println("Jeg har lavet hold i matches");
                    data.registerMatches(Main.DBMatches);
                }
                else if(Main.currentTeams.size() == 8) {
                    System.out.println("Jeg har lavet hold i quaterfinals");
                    data.registerMatches(Main.quarterFinals);
                }
                else if(Main.currentTeams.size() == 4) {
                    data.registerMatches(Main.semifinals);
                }
                else if(Main.currentTeams.size() == 2){
                    data.registerMatches(Main.Finals);
                }
                mainInterface();
                break;
            case 3:
                mainInterface();
                break;
            case 4:
                //Main.matches = Main.readMatchData("src/match.txt");
                System.out.println(Main.matches);
                break;
            default:
                System.out.println("Not a valid option");

        }

    }

    public void tournamentMenu()
    {

        System.out.println("\nTOURNAMENT MENU - TOURNAMENT " + Main.DBTournament.get(Main.tourChoose).getTournamentName().toUpperCase());
        System.out.println(" 1.Create tournament");
        System.out.println(" 2.Delete tournament"); //skal kigges p??
        System.out.println(" 3.Tournament placements"); //skal kigges p??
        System.out.println(" 4.Tournament match schedule"); //skal kigges p??
        System.out.println(" 5.Tournament simulation");
        System.out.println(" 6.Back to menu");
        System.out.print("UserInput: ");
        userInput = interfaceScan.nextInt();

        switch (userInput)
        {
            case 1:
                data.createTournament();
                mainInterface();
                break;
            case 2:
                data.deleteTournament();
                mainInterface();
                break;
            case 3:
                tournamentPlacement();
                mainInterface();
                break;
            case 4:
                data.tournamentSchedule();
                mainInterface();
                break;
            case 5:
                data.tournamentSim();
                mainInterface();
                break;
            case 6:
                mainInterface();
                break;
            default:
                System.out.println("Not a valid option");

        }

    }

    public void tournamentPlacement()
    {
        System.out.println("Stats for Matches" + "\n");

        System.out.println("Gruppespil");
        System.out.println(Main.matches + "\n");
        System.out.println("QuarterFinals");
        System.out.println(Main.quarterFinals + "\n");
        System.out.println("SemiFinals");
        System.out.println(Main.semifinals + "\n");
        System.out.println("Final");
        System.out.println(Main.Finals + "\n");

    }

}

