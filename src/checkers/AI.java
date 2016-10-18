/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author sdm29
 */
public class AI extends Player{
    Difficulty difficulty;
    ArrayList<String> names;
    
    AI(String n, Difficulty difficulty){
        super(n); 
        names.addAll(Arrays.asList("RNJesus","Dicey Dave", "Flipping Fred", "Random Ryan", "Chancy Chad"));
        names.trimToSize();
        name = rndName();
        
        this.difficulty = difficulty;
    }
    
    private String rndName(){
        Random ran = new Random();
        int R = ran.nextInt(names.size());
        return(names.get(R));
    }
}
