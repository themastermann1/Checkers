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
    ArrayList<Position> validPos = new ArrayList<>();
    //ArrayList<Position> availablePos = new ArrayList<>();     //used in minimax????? 
    ArrayList<Checker> pieces = new ArrayList<>();      //encoding of board?
    
    private boolean canTake;
    private int[] odd;
    private int[] even;   
    int teamCnt;                        //no longer in use

    public Board() {
        this.even = new int[]{0, 2, 4, 6};
        this.odd = new int[]{1, 3, 5, 7};
        pieces = new ArrayList<>();
        teamCnt = 12;                   //no longer in use
        canTake = false;
        
        initValidPos();
        ArrayList<Position> bPos =  new ArrayList<>(validPos.subList(20, 32));
        initBlack(bPos);  
        ArrayList<Position> rPos =  new ArrayList<>(validPos.subList(0, 12));
        initRed(rPos);    
        
        //availablePos = validPos;
        //for(Position p : validPos){
        //    print(p.toString());
        //}
        
        updateBoard();
    }
    
    //fill this out
    public void humanTurn(Player human){
        print("Pick the piece you want to move");
        Position moveStart = getUserMove();
        print(moveStart.toString());
        
        print("Pick the location you want to move to");
        Position moveEnd = getUserMove();
        print(moveEnd.toString());
        
        movePiece(moveStart, moveEnd, human.getTeam());
    }
    
    //fill this out
    public void AITurn(Player ai){
        Position[] move = randMove(ai.getTeam());
        print(move[0].toString());
        print(move[1].toString());
        
        //make the move
        movePiece(move[0], move[1], ai.getTeam());
    }
    
    //Move piece from start to end location.
    //No validation
    public void movePiece(Position start, Position end, Colour team){
        if(validateMove(start, end, team)){
            for(Checker c : pieces){
                if(c.getPos().x == start.x && c.getPos().y == start.y){
                    c.move(end);
                }
            }
            updateBoard();
        }else{
            print("oops, looks like thats not a valid move!");
            System.exit(1);         //Invalid move, break and throw some sort of error
        }
    }   
    
    //Check that the start location corrisponds to a piece and the piece is alive.
    //check that the end move is on an available space (clear and valid)
    //add check that piece is of correct colour
    public boolean validateMove(Position start, Position end, Colour team){
        for(Checker c : pieces){
            if(c.getPos().x == start.x && c.getPos().y == start.y && c.isAlive() && c.getColour() == team){
                ArrayList<Position> availableMoves = getAvailableMoves(c);
                for(Position p : availableMoves){
                    //System.out.println(p);
                    if(p.x == end.x && p.y == end.y){
                        return(true); 
                    }
                }
            }
        }
        //System.exit(0);
        print("break0");
        return(false); 
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
    
    //print the board to the console
    public void displayBoard(){
        //print("  1 2 3 4 5 6 7 8");  
        print("\n");
        for (int i = 8; i > 0; --i) {
            for (int j = 0; j < 8; ++j) {
                // left-hand side column 1-8
                if(j == 0)
                    System.out.print((i));
                // pieces belonging to player 1 get an B (black)
                if(board[j][i-1] == 1)
                    System.out.print(" B");
                // pieces belonging to player 2 get an R (Red)
                else if(board[j][i-1] == 2) 
                    System.out.print(" R");
                // empty fields are indicated by a -
                else
                    System.out.print(" -");
            }
            System.out.println();
        }
        print("  1 2 3 4 5 6 7 8\n");   
    }
    
    //2d array containing the board state. Black = 1, Red = 2, empty = 0
    public void updateBoard(){
        int[][] b = new int[8][8];   
        for (Checker c : pieces) {
            Position p = c.getPos();
            if(c.colour == Colour.BLACK){
                b[p.x][p.y] = 1;
            }else if (c.colour == Colour.RED){
                b[p.x][p.y] = 2;
            }
        }
        board = b;
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
                    validPos.add(p);
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    Position p = new Position(odd[j], i);                  
                    validPos.add(p);
                }
            }
        }
    }
    
    //TO DO makework!
    //unused?
    public ArrayList<Position> getAvailablePos(Colour c){
        //caculate available moves for the defined colour
        //temp code, remove later
        
        //take in a piece
        //check to see if piece has a valid move
        
        return(null);
    }
    
    //random move, used by easy AI
    public Position[] randMove(Colour team){
        //Boolean b = false;
        Random r = new Random();
        getAvailablePos(team); // unused?
        Position[] p = new Position[2];
        p[0] = new Position(-1, -1);
        p[1] = new Position(-1, -1);
        
        while(!validateMove(p[0], p[1], team)){
            int i = r.nextInt(pieces.size());
            Checker c = pieces.get(i);
            if(c.isAlive() && c.getColour().equals(team)){
                p[0] = c.getPos();                
                
                p[1].y = p[0].y+1;          //set end position
                p[1].x = p[0].x+1;
            }  
        }
        return(p);
    }

    //TO DO makework!
    public boolean gameOver(){
        return false;
    }

    //return valid positions, unused?
    public ArrayList<Position> getValidPos() {
        return validPos;
    }
    
    //gets positions of possible moves for piece
    //messy AF, refactor.
    public ArrayList<Position> getAvailableMoves(Checker c){
        ArrayList<Position> availableMoves = new ArrayList<>();
        Position p = c.getPos();
        boolean lUp = true;
        boolean lDn = true;
        boolean rUp = true;
        boolean rDn = true;
        Position rMoveUp = new Position(p.x+1, p.y+1);
        Position lMoveUp = new Position(p.x-1, p.y+1);
        Position rMoveDn = new Position(p.x+1, p.y-1);
        Position lMoveDn = new Position(p.x-1, p.y-1);
        
        if(c.getRank() == Type.KING){
            //can move anydirection
            for(Checker piece : pieces){
                if(piece.getPos().x == lMoveUp.x && piece.getPos().y == lMoveUp.y && c.isAlive()){   //if location is free (empty or dead piece)
                    lUp = false; 
                    availableMoves.add(lMoveUp);
                }else if(piece.getPos().x == rMoveUp.x && piece.getPos().y == rMoveUp.y && c.isAlive()){
                    rUp = false;                    
                }else if(piece.getPos().x == lMoveDn.x && piece.getPos().y == lMoveDn.y && c.isAlive()){   //if location is free (empty or dead piece)
                    lDn = false;                   
                }else if(piece.getPos().x == rMoveDn.x && piece.getPos().y == rMoveDn.y && c.isAlive()){
                    rDn = false;     
                }
            } 
            if(lUp){
                availableMoves.add(lMoveUp);
            }else{          
                
            }
            if(rUp){
                availableMoves.add(rMoveUp);
            }
            if(lDn){
                availableMoves.add(lMoveDn);
            }
            if(rDn){
                availableMoves.add(rMoveDn);
            }
        }else if (c.getColour() == Colour.BLACK){
            //down left/right
            for(Checker piece : pieces){
                if(piece.getPos().x == lMoveDn.x && piece.getPos().y == lMoveDn.y && c.isAlive()){   //if location is free (empty or dead piece)
                    lDn = false; 
                }else if(piece.getPos().x == rMoveDn.x && piece.getPos().y == rMoveDn.y && c.isAlive()){
                    rDn = false; 
                }
            }   
            if(lDn){
                availableMoves.add(lMoveDn);
            }else{              // a piece is blocking this move
                //check to see if blocking piece is from other team
                //if true, check left and down.
                //make jump true
                //make sure to only return jump moves as possible
            }
            if(rDn){
                availableMoves.add(rMoveDn);
            }
        }else if (c.getColour() == Colour.RED){
            //up left/right
            for(Checker piece : pieces){
                if(piece.getPos().x == lMoveUp.x && piece.getPos().y == lMoveUp.y && c.isAlive()){   //if location is occupied
                    lUp = false;                                                                     //set position to false           
                }else if(piece.getPos().x == rMoveUp.x && piece.getPos().y == rMoveUp.y && c.isAlive()){
                    rUp = false; 
                }  
            }
            if(lUp){
                availableMoves.add(lMoveUp);
            }
            if(rUp){
                availableMoves.add(rMoveUp);
            }
            
        }
        for(Position pos:availableMoves){
            //print(pos.toString());
        }
        return (availableMoves);
    }
    
    //TO DO make work!
    public Colour getWinner(){
        //move this to the checkers class?
        return Colour.RED;
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
