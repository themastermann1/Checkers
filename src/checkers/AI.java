/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

/**
 *
 * @author sdm29
 */
public class AI extends Player{
    Difficulty difficulty;
    
    AI(String n, Difficulty difficulty){
        super(n); 
        name = rndName();
        this.difficulty = difficulty;
    }
    
    private String rndName(){
        return("fred");
    }
}
