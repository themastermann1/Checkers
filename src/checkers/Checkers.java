/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.io.PrintStream;

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
        //runGame(processInput(getUserInput())); 
    }
       
    private static void runGame(int playerPos){
        Board b = new Board();
        Player p1 = new Player();
        Player p2 = new Player();
        
        //setting up the players
        p1.setTeam(Colour.BLACK);
        p2.setTeam(Colour.RED);
        
        //setting which player goes first
        if (playerPos == 1){
            p1.setHuman();
            p2.setAI();
        }else{
            p2.setHuman();
            p1.setAI();
        }
        /*
        while(!b.gameFinished){
            if(player1.type = AI){
                AITurn();
                humanTurn();
            }else{
                humanTurn();
                AITurn();
            }
            
            //player 1 turn
            
            //player 2 turn
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
    
    private static int processInput(){
        return(1);
        //add an exit statment for then ther users wants to quit
    }
    
    private static String getUserInput(){
        //use scanner calss for easy
        return("s");
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
