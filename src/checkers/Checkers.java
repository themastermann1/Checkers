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
        //welcome message
        print("Welcome to checkers MK:I");
        runGame(processInput(getUserInput()));
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
        
        while(!b.gameOver()){
            if(p1.getType() == Controller.AI){
                print("The AI " + p1.getName() + " is taking its turn");
                b.AITurn();
                print("Its your turn " + p2.getName() + " make your move!!!");
                print("(x,y)");
                b.humanTurn();
            }else{
                print("Its your turn " + p1.getName() + " make your move!!!");
                print("(x,y)");
                b.humanTurn();
                print("The AI " + p2.getName() + " is making moves!");
                b.AITurn();
            }
            System.exit(0);
        }
        
        //dispaly winning / defeat message
        Colour winner = b.getWinner();
        if(p1.getTeam() == winner){
            print("Player: " + p1.getName() + " Takes the game!");
        } else {
            print("Player: " + p2.getName() + " Wins the match!");
        }
        
        
    //fix this recursion, if statment or something?
    print("\n Rematch?");
    //runGame(processInput(getUserInput()));    
    }    
    
    //move this shit to board class?
    private static void humanTurn(){
        //run minimax and display "reccomended" moves
        //get piece to move
        //get location to move piece
        //validate the move - if can double take prompt user for another pos.
        //move piece
        //b.updateBoard();
    }
    
    //move this shit to board class?
    private static void AITurn(){
        //run minimax
        //move piece
        //b.updateBoard();
    }
    
    //process menu imput
    private static int processInput(String s){
        try{
            return(Integer.parseInt(s));
        }catch(Exception e){    //change this to a better exception?
            print("User Input error.");
            System.exit(1);     //non zero exit argument, consider adding something that uses this. 
        }
        return(0);              //this return statment should never be called...
    }
    
    //get basic menu input from user
    private static String getUserInput(){
        //use scanner calss for easy
        Scanner scanner = new Scanner(System.in);
        print("\n Enter 1 to pick black and move first, or 2 to pick red and go second.");
        print("Alternativly type \"Quit\" to cole the program: ");
        String in = scanner.next();
        
        if(in.equals("Quit")){
            print("So long... Thanks for all the fish!!!");
            //quit the program
            System.exit(0); //0 for standard shutdown
        }
        return(in);
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
