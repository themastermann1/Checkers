/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author sdm29
 */
public class Board {

    int[][] board = new int[8][8];
    ArrayList<Position> validPos;
    ArrayList<Position> availablePos; 
    ArrayList<Checker> pieces;
    
    private int[] even = {1, 3, 5, 7};
    private int[] odd = {0, 2, 4, 6};
    
    //public int[][] validPos = new int[32][2];
    //number of pieces on each team
    int teamCnt;

    public Board() {
        pieces = new ArrayList<>();
        teamCnt = 12;
        
        initValidPos();
        ArrayList<Position> bPos =  new ArrayList<>(validPos.subList(0,12));
        initBlack(bPos);  
        ArrayList<Position> rPos =  new ArrayList<>(validPos.subList(20,32));
        initRed(rPos);    
    }
    
    public ArrayList<Position> getAvailablePos(){
        return null;
    }
    
    //fill this out
    public void humanTurn(){
        print("Pick the piece you want to move");
        Position moveStart = getUserMove();
        print(moveStart.toString());
        print("Pick the location you want to move to");
        Position moveEnd = getUserMove();
        print(moveEnd.toString());
    }
    
    //fill this out
    public void AITurn(){
        Position moveStart = randMove();
        print(moveStart.toString());
        Position moveEnd = randMove();
        print(moveEnd.toString());
    }
    
    //fill this out
    public void movePiece(Position start, Position end){
        //try
        //board.getPiece(pos)
        //move to end
    }
    
    //get move coordinates from the user, return as a Position
    private static Position getUserMove(){
        //scanner
        Scanner scanner = new Scanner(System.in);
        int xin = scanner.nextInt()-1;      //adjust for 0 index
        int yin = scanner.nextInt()-1;
        Position p = new Position(xin, yin);
        return(p);
    }
    
    //TO DO make work
    public void validateMove(){
        
    }
    
    //TO DO make work
    public void evaluateBoard(){
        //minimax();
    }

    //initilise the pieces for the red team
    public void initRed(ArrayList<Position> redPos) {
        for (Position p : redPos) {
            Checker c = new Checker(Colour.RED, p);
            pieces.add(c);
        }
    }
    
    //initilise the pieces for the black team
    public void initBlack(ArrayList<Position> blackPos) {
        for (Position p : blackPos) {
            Checker c = new Checker(Colour.BLACK, p);
            pieces.add(c);
        }
    }
    
    //initilise the list of valid positions
    //old code, refactor (partial refactor complete)
    public void initValidPos(){
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 4; j++) {
                    Position p = new Position(even[j], i);                  
                    this.validPos.add(p);
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    Position p = new Position(odd[j], i);                  
                    this.validPos.add(p);
                }
            }
        }
    }
    
    //return valid positions, unused?
    public ArrayList<Position> getValidPos() {
        return validPos;
    }
    
    //TO DO makework!
    public boolean gameOver(){
        return false;
    }
    
    //TO DO make work!
    public Colour getWinner(){
        //move this to the checkers class?
        return Colour.RED;
    }
    
    //random move
    public Position randMove(){
        Random r = new Random();
        int x = r.nextInt(8);
        int y = r.nextInt(8);
        Position p = new Position(x,y);
        return p;
    }
    
    //test methods, delete later.
    public int[] getEven() {
        return even;
    }

    //test methods, delete later.
    public int[] getOdd() {
        return odd;
    }
    
    //makes printing to the console a little nicer :)
    private static void print(String s){
        System.out.println(s);
    }
}
