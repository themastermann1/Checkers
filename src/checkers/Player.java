/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

/**
 *
 * @author sdm29
 */
public class Player {
    String name;
    Colour team;
    Controller type;
    Difficulty diff;
    int score;
    
    Player(){
    }
    
    public void setTeam(Colour colour){
        team = colour;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setController(Controller c){
        this.type = c;
    }
    
    public void setDifficulty(Difficulty d){
        this.diff = d;
    }
    
    public String getName() {
        return name;
    }

    public Colour getTeam() {
        return team;
    }

    public Controller getType() {
        return type;
    }

    public Difficulty getDiff() {
        return diff;
    }

    public int getScore() {
        return score;
    }
}
