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
    private Difficulty difficulty;
    private ArrayList<String> names;
    
    AI(String n, Difficulty difficulty){
        super(n); 
        names = new ArrayList<>();
        
        initNames();
        this.difficulty = difficulty;
    }
    
    private void initNames(){
        //change this to stooring the names in a simple .txt file? allows addintion of names to be saved
        //and names of high scooring players to be added to the list for the AI to pick from
        //even add thhings like if ben is top scorer add a randomly generated name based on "ben" to the list, 
        //ie bens dad, chess guru ben, bens ghost etc.
        this.names.addAll(Arrays.asList("RNJesus","Dicey Dave", "Flipping Fred", "Random Ryan", "Chancy Chad"));
        this.names.trimToSize();
        this.name = rndName();
    }
    
    private String rndName(){
        Random ran = new Random();
        int R = ran.nextInt(names.size());
        return(names.get(R));
    }
    
    public void addName(String n){
        //lets users add names to list that the AI picks from.
        this.names.add(n);
    }
    
    public String getName(){
        return this.name;
    }
}
