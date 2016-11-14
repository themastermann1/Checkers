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
    int score;
    
    Player(String name){
        this.name = name;
    }
    
    public void setTeam(Colour colour){
        team = colour;
    }
    
    public void setName(String name){
        this.name = name;
    }
}
