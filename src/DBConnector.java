import com.sun.security.jgss.GSSUtil;

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
                Team team = new Team(teamName,knockOut,tournament_id,match_id);
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

                Match match = new Match(team1,team2,tournament_id,goal1,goal2,active);
                match.setId(id);
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

    @Override
    public void saveTeamData()
    {
        ResultSet rs = null;
        Connection conn = null;
        //Statement stmt = null;

        String sql = "INSERT INTO Teams (tournament_id, match_id, name, knockOut)"
                + " VALUES(?,?,?,?)";


        try
        {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            //STEP 2: Execute a query
            System.out.println("Creating statement...");
            //  stmt = conn.createStatement();

            for (int i = 0; i < Main.DBAllTeams.size(); i++)
            {
                System.out.println("JEG ER INDE ");
                pstmt.setInt(1, Main.DBAllTeams.get(i).getTournament_id());
                pstmt.setInt(2, Main.DBAllTeams.get(i).getMatch_id());
                pstmt.setString(3, Main.DBAllTeams.get(i).getTeamName());
                pstmt.setBoolean(4, Main.DBAllTeams.get(i).isKnockedOut());

                pstmt.addBatch();
            }


            pstmt.executeBatch();


        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }finally {
            try{
                if(rs != null) rs.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("statement Done");
    }

    @Override
    public void saveMatchData()
    {
        ResultSet rs = null;
        Connection conn = null;
        //Statement stmt = null;

        String sql = "INSERT INTO Matches (tournament_id, team1, team2 , goal1, goal2, active)"
                + " VALUES(?,?,?,?,?,?)";


        try
        {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            //STEP 2: Execute a query
            System.out.println("Creating statement...");
            //  stmt = conn.createStatement();

            for (int i = 0; i < Main.DBAllMatches.size(); i++)
            {
                System.out.println("JEG ER INDE ");
                pstmt.setInt(1, Main.DBAllMatches.get(i).getTournament_id());
                pstmt.setString(2, Main.DBAllMatches.get(i).getTeam1Name());
                pstmt.setString(3, Main.DBAllMatches.get(i).getTeam2Name());
                pstmt.setInt(4, Main.DBAllMatches.get(i).getTeam1Goals());
                pstmt.setInt(5, Main.DBAllMatches.get(i).getTeam2Goals());
                pstmt.setBoolean(6, Main.DBAllMatches.get(i).isActive());

                pstmt.addBatch();
            }


            pstmt.executeBatch();


        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }finally {
            try{
                if(rs != null) rs.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("statement Done");
    }
}
