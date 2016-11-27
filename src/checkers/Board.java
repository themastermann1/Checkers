/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.util.ArrayList;

/**
 *
 * @author sdm29
 */
public class Board {

    /*
    int[][] board = new int[8][8];
    ArayList<Position> validPos;
    ArrayList<Position> availablePos; 
    ArrayList<Checker> pieces();
    */
    
    private ArrayList<Checker> board;
    private int[] even = {1, 3, 5, 7};
    private int[] odd = {0, 2, 4, 6};
    public int[][] validPos = new int[32][2];
    //number of pieces on each team
    int teamCnt;

    public Board() {
        board = new ArrayList<>();
        teamCnt = 12;
        validPos[31][0]=1;
        initValidPos(validPos);
    }
    
    public boolean gameOver(){
        return false;
    }
    
    public Colour getWinner(){
        //move this to the checkers class?
        return Colour.RED;
    }
    
    public ArrayList<Position> getAvailablePos(){
        return null;
    }
    
    public void humanTurn(){
        
    }
    
    public void AITurn(){
        
    }
    
    public void movePiece(Position start, Position end){
        //try
        //board.getPiece(pos)
        //move to end
    }
    
    public void validateMove(){
        
    }
    
    public void evaluateBoard(){
        //minimax();
    }

    //initilise the pieces for the black team
    public void initRed() {
        // put a black piece in the first teamCnt valid postions
        for (int i = 0; i < teamCnt; i++) {
            Position p = new Position(validPos[i][0], validPos[i][1]);
            Checker c = new Checker(Colour.RED, p);
            board.add(c);
        }
    }

    public void initBlack() {
        // put a red piece in the last teamCnt valid postions
        for (int i = 31; (32 - teamCnt) > i; i--) {
            Position p = new Position(validPos[i][0], validPos[i][1]);
            Checker c = new Checker(Colour.BLACK, p);
            board.add(c);
        }
    }
    
    public void initValidPos(int[][]x){
        int[][] vPos = x;
        int c = 0;
                
        for (int i = 0; i < 8; i++) {
            vPos[31][0]=1;
            if (i % 2 == 0) {
                for (int j = 0; j < 4; j++) {
                    vPos[c][0] = even[j];
                    vPos[c][1] = i;
                    c++;
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    vPos[c][0] = odd[j];
                    vPos[c][1] = i;
                    c++;
                }
            }
        }
        validPos[31][0]=1;
        this.validPos = vPos;
    }
    
    //test methods, delete later.
    public int[] getEven() {
        return even;
    }

    public int[] getOdd() {
        return odd;
    }
        
    public int[][] getValidPos() {
        //validPos[31][0]=1;
        return validPos;
    }
}
