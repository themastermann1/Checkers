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

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //welcome message
        System.out.println("Welcome to checkers MK:I");
        //runGame(processInput(getUserInput())); 
    }
       
    private static void runGame(int playerPos){
        Board b = new Board();
        
        //setting up the players
        Player p1 = new Player();
        Player p2 = new Player();
        
        p1.setTeam(Colour.BLACK);
        p1.setName("Player1");
        p2.setTeam(Colour.RED);
        p2.setName("Player2");
        
        //setting which player goes first
        if (playerPos == 1){
            p1.setController(Controller.HUMAN);
            p2.setController(Controller.AI);
        }else{
            p2.setController(Controller.HUMAN);
            p1.setController(Controller.AI);
        }
        /*
        while(!b.gameFinished){
            if(player1.type = AI){
                b.AITurn();
                b.humanTurn();
            }else{
                b.humanTurn();
                b.AITurn();
            }
            
        }
        
        dispaly winning / defeat message
        
        */
    //fix this recursion if statment or something?
    System.out.println("\n Rematch?");
    //runGame(processInput(getUserInput()));    
    }    
    
    private static void humanTurn(){
        //run minimax and display "reccomended" moves
        //get piece to move
        //get location to move piece
        //validate the move - if can double take prompt user for another pos.
        //move piece
        //b.updateBoard();
    }
    
    private static void AITurn(){
        //run minimax
        //move piece
        //b.updateBoard();
    }
    
    private static int processInput(String s){
        try{
            return(Integer.parseInt(s));
        }catch(Exception e){    //change this to a better exception?
            System.out.println("User Input error.");
            System.exit(1);     //non zero exit argument, consider adding something that uses this. 
        }
        return(0);              //this return statment should never be called...
    }
    
    private static String getUserInput(){
        //use scanner calss for easy
        Scanner scanner = new Scanner(System.in);
        String out = new String();
        System.out.println("/n Enter 1 to pick black and move first, or 2 to pick red and go second.");
        System.out.println("Alternativly type \"Quit\" to cole the program: ");
        String in = scanner.next();
        
        if(in.equals("Quit")){
            System.out.println("So long... Thanks for all the fish!!!");
            //quit the program
            System.exit(0); //0 for standard shutdown
        }else{
            out = in;
        }
        return(out);
    }
    
    //get coordinates from the user
    private static int[] getUserMove(){
        int[] move = new int[2];
        //scanner
        move[0]=1;
        move[1]=2;
        
        move[0]++;
        move[1]++;
        return(move);
    }
    
    
    //does nothing
    public Checkers(){
        //Game = new Game();  
        //get user input
    }
}
