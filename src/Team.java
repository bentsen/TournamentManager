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

    public Team(String teamName, boolean knockedOut){
        this.teamName = teamName;
        this.knockedOut = knockedOut;

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

    public void getPlacement(){

    }

    public void setPlacement(){

    }

    public boolean isKnockedOut() {
        return knockedOut;
    }

    @Override
    public String toString() {
        return  teamName;
    }

}
