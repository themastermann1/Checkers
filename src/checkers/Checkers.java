/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author sdm29
 */
public class Checkers {
    
    static GUI gui = new GUI();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //welcome message
        print("Welcome to checkers MK:I");
        //get user inupt from board?
        gui.runGame(3);
    }
    
    //makes printing to the console a little nicer :)
    private static void print(String s){
        System.out.println(s);
    }
    
    //does nothing
    public Checkers(){
        //Game = new Game();  
        //get user input
    }
}
