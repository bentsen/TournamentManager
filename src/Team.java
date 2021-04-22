import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Scanner;

public class Team {
    String teamName;
    int points;
    boolean knockedOut = false;
    UI ui;
    private int tournament_id;

    public Team(String teamName, boolean knockedOut, int tournament_id){
        this.teamName = teamName;
        this.knockedOut = knockedOut;
        this.tournament_id = tournament_id;

    }

    public String getTeamName() {
        return teamName;
    }

    public int getPoints(){
        return points;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public void setKnockedOut(boolean knockedOut) {
        this.knockedOut = knockedOut;
    }

    public void getPlacement(){ }

    public void setPlacement(){ }

    public void setTournament_id(int tournament_id) { this.tournament_id = tournament_id; }

    public int getTournament_id() { return tournament_id; }

    public boolean isKnockedOut() {
        return knockedOut;
    }

    @Override
    public String toString() {
        return  teamName;
    }

}
