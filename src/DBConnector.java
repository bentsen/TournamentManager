import java.util.ArrayList;
import java.sql.*;
public class DBConnector implements IO
{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/TournamentManager";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "peugeot405";

    @Override
    public ArrayList<Tournament> readTournamentData()
    {
        ArrayList<Tournament> tournamentList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            // Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();


            String sql = "SELECT * FROM Tournaments";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id = rs.getInt("id");
                String name  = rs.getString("name");

                //Display values
                System.out.print("Tournament-ID: " + id);
                System.out.println(" | TournamentName: " + name);
                Tournament tournament = new Tournament(name,id);
                tournamentList.add(tournament);

            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try





        return tournamentList;
    }
    @Override
    public ArrayList<Team> readTeamData()
    {
        ArrayList<Team> teamList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            // Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Teams";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int tournament_id = rs.getInt("tournament_id");
                int match_id = rs.getInt("match_id");
                String teamName = rs.getString("name");
                boolean knockOut = rs.getBoolean("knockOut");

                //Display values
                System.out.print("Tournament-ID: " + tournament_id);
                System.out.print(" | Match-ID: " + match_id);
                System.out.print(" | TeamName: " + teamName);
                System.out.println(" | TeamknockOut: " + knockOut);
                Team team = new Team(teamName,knockOut,tournament_id);
                teamList.add(team);


            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return teamList;
    }
    @Override
    public ArrayList<Match> readMatchData()
    {
        ArrayList<Match> matchList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            // Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Matches";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id = rs.getInt("id");
                int tournament_id = rs.getInt("tournament_id");
                String team1 = rs.getString("team1");
                String team2 = rs.getString("team2");
                int goal1 = rs.getInt("goal1");
                int goal2 = rs.getInt("goal2");
                boolean active = rs.getBoolean("active");

                //Display values
                System.out.print("Match-ID: " + id);
                System.out.print(" | Tournament-ID: " + tournament_id);
                System.out.print(" | Team1Name: " + team1);
                System.out.print(" | Team2Name: " + team2);
                System.out.print(" | Team1Goals: " + goal1);
                System.out.println(" | Team2Goals: " + goal2);
                System.out.println(" | active: " + active);

                Match match = new Match(team1,team2,id,tournament_id,goal1,goal2,active);
                matchList.add(match);


            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return matchList;
    }
}
