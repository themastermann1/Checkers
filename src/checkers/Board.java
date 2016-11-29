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
    ArrayList<Move> allMoves = new ArrayList<>();
    
    //private boolean canTake;
    private int[] odd;
    private int[] even;   
    int teamCnt;                        //no longer in use

    public Board() {
        this.even = new int[]{0, 2, 4, 6};
        this.odd = new int[]{1, 3, 5, 7};
        pieces = new ArrayList<>();
        teamCnt = 12;                   //no longer in use
        //canTake = false;
        
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
        
        Move m = new Move(moveStart, moveEnd);
        
        movePiece(m, human.getTeam());
    }
    
    //fill this out
    public void AITurn(Player ai){
        Move m = randMove(ai.getTeam());
        print(m.getStart().toString());
        print(m.getEnd().toString());
        
        //make the move
        movePiece(m, ai.getTeam());
    }
    
    //Move piece from start to end location.
    public void movePiece(Move mov, Colour team){
        if(validateMove(mov, team)){
            Move m = getMove(mov);          //get some try catch action going on
            for(Checker c : pieces){
                if(c.getPos().x == m.getStart().x && c.getPos().y == m.getStart().y && c.alive){
                    c.move(m.getEnd());
                    print("breake move");
                    if(m.getTake() == true){
                        int x = m.getStart().x + m.getEnd().x;
                        int y = m.getStart().y + m.getEnd().y;
                        Position p = new Position(x/2, y/2);
                        print(p.toString());
                        for(Checker c2 : pieces){
                            if(c2.getPos() == p && c2.alive){                                
                                c2.kill();                     //c2 has been taken, rip.
                                if(c2.getRank() == Type.KING){      //kill a king become a king
                                    c.king();
                                }
                            }
                        }
                    }
                    //kinging a piece that reaches the end of the board
                    if(c.getColour() == Colour.RED && c.getPos().y == 7){
                        c.king();
                        break;      //once kinged move ends
                    }else if(c.getColour() == Colour.BLACK && c.getPos().y == 0){
                        c.king();
                        break;
                    }
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
    public boolean validateMove(Move m, Colour team){
        for(Checker c : pieces){
            if(c.getPos().x == m.getStart().x && c.getPos().y == m.getStart().y && c.isAlive() && c.getColour() == team){
                for(Move mov : this.allMoves){
                    if(mov.getStart().x == m.getStart().x && mov.getStart().y == m.getStart().y){   //start move is valid
                        if(mov.getEnd().x == m.getEnd().x && mov.getEnd().y == m.getEnd().y){   //end move is valid
                            return(true); 
                        }
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
                // pieces belonging to player 1 get an b (black)
                if(board[j][i-1] == 1)
                    System.out.print(" b");
                // pieces belonging to player 2 get an r (red)
                else if(board[j][i-1] == 2) 
                    System.out.print(" r");
                // kings belonging to player 1 get an B (Black)
                else if(board[j][i-1] == 3)
                    System.out.print(" B");
                // kings belonging to player 2 get an R (Red)
                else if(board[j][i-1] == 4) 
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
            if(c.alive){
                Position p = c.getPos();
                if(c.colour == Colour.BLACK){
                    if(c.rank == Type.KING){
                        b[p.x][p.y] = 3;
                    }else{
                        b[p.x][p.y] = 1;
                    }
                }else if (c.colour == Colour.RED){
                    if(c.rank == Type.KING){
                        b[p.x][p.y] = 4;
                    }else{
                        b[p.x][p.y] = 2;
                    }
                }
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
    public Move getMove(Move m){
        for(Move mov : allMoves){
            //ewwwwww shitty if statment :(
            //ovveride .equals() so no need for this shit
            if(mov.getStart().x == m.getStart().x && mov.getEnd().x == m.getEnd().x && mov.getStart().y == m.getStart().y && mov.getEnd().y == m.getEnd().y){
                return(mov);
            }
        }
        print("no move");
        return(null);
    }
    
    //random move, used by easy AI
    public Move randMove(Colour team){
        //Boolean b = false;
        Random r = new Random();
        Position p1 = new Position(-1, -1);
        Position p2 = new Position(-1, -1);
        Move m = new Move(p1,p2);
        
        while(!validateMove(m, team)){
            int i = r.nextInt(pieces.size());
            Checker c = pieces.get(i);
            if(c.isAlive() && c.getColour().equals(team)){
                m.setStart(c.getPos());                
                
                //randomly pick to more left or right
                p2 = m.getStart();
                p2.y += 1;
                if(r.nextInt(2)==0){          //set end position
                    p2.x += 1;
                }else{
                    p2.x -= 1;
                }
                m.setEnd(p2);
            }  
        }
        return(m);
    }
    
    //returns a list of all moves currently possible 
    public void getAllAvailableMoves(Colour col){
        ArrayList<Move> allMoves = new ArrayList<>();
        ArrayList<Move> allMovesT = new ArrayList<>();
        boolean take = false;
        for(Checker c : pieces){
            if(c.alive && c.getColour() == col){
                ArrayList<Move> movs = new ArrayList<>();
                movs = getAvailableMoves(c);
                for(Move m : movs){    
                    if(m.getTake()==true){
                        allMovesT.add(m);
                        take = true;
                    }else{
                        allMoves.add(m);
                        
                    }
                }    
            }
        }
        if(take){
            this.allMoves = allMovesT;
        }else{
            this.allMoves = allMoves;    
        }
        for(Move m : this.allMoves){
            print(m.getStart().toString() + m.getEnd().toString());
        }
    }    
    
    //returns a list of moves available for the passed checker
    //messy AF, refactor.
    public ArrayList<Move> getAvailableMoves(Checker c){
        ArrayList<Move> availableMoves = new ArrayList<>();
        Position p = c.getPos();
        boolean lUp = true;
        boolean lDn = true;
        boolean rUp = true;
        boolean rDn = true;
        
        boolean takeL, takeR, takeL2, takeR2;
        takeL = false;
        takeR = false;
        takeL2 = false;
        takeR2 = false;
        
        Position rMoveUp = new Position(p.x+1, p.y+1);
        Position lMoveUp = new Position(p.x-1, p.y+1);
        Position rMoveDn = new Position(p.x+1, p.y-1);
        Position lMoveDn = new Position(p.x-1, p.y-1);
        
        if(c.getRank() == Type.KING){
            //can move anydirection
            /*
            for(Checker piece : pieces){
                if(piece.getPos().x == lMoveUp.x && piece.getPos().y == lMoveUp.y && c.isAlive()){   //if location is free (empty or dead piece)
                    lUp = false; 
                    if(!piece.getColour().equals(c.getColour())){   //check to see if blocking piece is from other team
                        for(Checker piece2 : pieces){
                            if(piece2.getPos().x == lMoveUp.x-1 && piece2.getPos().y == lMoveUp.y+1 && c.isAlive()){    
                                //no free space, cannot take
                            }else{
                                //free space behind, take available
                                takeL = true;         //signal that a piece can be taken, 
                            }
                        }
                    }    
                }else if(piece.getPos().x == rMoveUp.x && piece.getPos().y == rMoveUp.y && c.isAlive()){
                    rUp = false;
                    if(!piece.getColour().equals(c.getColour())){   //check to see if blocking piece is from other team
                        for(Checker piece2 : pieces){
                            if(piece2.getPos().x == rMoveUp.x+1 && piece2.getPos().y == rMoveUp.y+1 && c.isAlive()){    
                                //no free space, cannot take
                            }else{
                                //free space behind, take available
                                takeR = true;         //signal that a piece can be taken, 
                            }
                        }
                    }
                }else if(piece.getPos().x == lMoveDn.x && piece.getPos().y == lMoveDn.y && c.isAlive()){   //if location is free (empty or dead piece)
                    lDn = false;       
                    if(!piece.getColour().equals(c.getColour())){          
                        for(Checker piece2 : pieces){
                            if(piece2.getPos().x == lMoveDn.x-1 && piece2.getPos().y == lMoveDn.y-1 && c.isAlive()){
                                //no free space, cannot take
                            }else{
                                //free space behind, take available
                                takeL = true;         //signal that a piece can be taken, 
                            }
                        }
                    }
                }else if(piece.getPos().x == rMoveDn.x && piece.getPos().y == rMoveDn.y && c.isAlive()){
                    rDn = false;   
                    if(!piece.getColour().equals(c.getColour())){   //check to see if blocking piece is from other team
                        for(Checker piece2 : pieces){
                            if(piece2.getPos().x == rMoveDn.x-1 && piece2.getPos().y == rMoveDn.y-1 && c.isAlive()){    
                                //no free space, cannot take
                            }else{
                                //free space behind, take available
                                takeR = true;         //signal that a piece can be taken, 
                            }
                        }
                    }
                }
            } 
            if(lUp){
                Move m = new Move(c.getPos(), lMoveUp);
                availableMoves.add(m);
            }else if(takeL == true) {           
                lMoveUp.x -=1;
                lMoveUp.y +=1;
                Move m = new Move(c.getPos(), lMoveUp);
                m.setTake(true);
                availableMoves.add(m);
            }     
            if(rUp){
                Move m = new Move(c.getPos(), rMoveUp);
                availableMoves.add(m);
            }else if(takeR == true) {           
                rMoveUp.x +=1;
                rMoveUp.y +=1;
                Move m = new Move(c.getPos(), rMoveUp);
                m.setTake(true);
                availableMoves.add(m);
            }
            if(lDn){
                Move m = new Move(c.getPos(), lMoveDn);
                availableMoves.add(m);
            }else if(takeL2 == true) {        
                lMoveDn.x -=1;
                lMoveDn.y -=1;
                Move m = new Move(c.getPos(), lMoveDn);
                m.setTake(true);
                availableMoves.add(m);
            }
            if(rDn){
                Move m = new Move(c.getPos(), rMoveDn);
                availableMoves.add(m);
            }else if(takeR2 == true) {           
                lMoveDn.x +=1;
                lMoveDn.y -=1;
                Move m = new Move(c.getPos(), rMoveDn);
                m.setTake(true);
                availableMoves.add(m);
            }
             */
        }else if (c.getColour() == Colour.BLACK){
            //down left/right
            for(Checker piece : pieces){
                if(piece.getPos().x == lMoveDn.x && piece.getPos().y == lMoveDn.y && c.isAlive()){   //if location is not free (empty or dead piece)
                    lDn = false; 
                    if(!piece.getColour().equals(c.getColour())){          
                        for(Checker piece2 : pieces){
                            takeL = true;
                            if(piece2.getPos().x == lMoveDn.x-1 && piece2.getPos().y == lMoveDn.y-1 && piece2.isAlive()){
                                //no free space, cannot take
                                takeL = false; 
                                break;
                            }else{
                                //free space behind, take available
                                takeL = true;         //signal that a piece can be taken, 
                            }
                        }
                    }
                }else if(piece.getPos().x == rMoveDn.x && piece.getPos().y == rMoveDn.y && c.isAlive()){
                    rDn = false;  
                    if(!piece.getColour().equals(c.getColour())){   //check to see if blocking piece is from other team
                        for(Checker piece2 : pieces){
                            takeR = true;
                            if(piece2.getPos().x == rMoveDn.x+1 && piece2.getPos().y == rMoveDn.y-1 && piece2.isAlive()){    
                                //no free space, cannot take
                                takeR = false; 
                                break;
                            }else{
                                //free space behind, take available
                                takeR = true;         //signal that a piece can be taken, 
                            }
                        }
                    }
                }
            }   
            if(lDn){
                Move m = new Move(c.getPos(), lMoveDn);
                availableMoves.add(m);
            }else if(takeL == true) {        
                lMoveDn.x -=1;
                lMoveDn.y -=1;
                Move m = new Move(c.getPos(), lMoveDn);
                m.setTake();
                availableMoves.add(m);
            }
            if(rDn){
                Move m = new Move(c.getPos(), rMoveDn);
                availableMoves.add(m);
            }else if(takeR == true) {   
                rMoveDn.x +=1;
                rMoveDn.y -=1;
                Move m = new Move(c.getPos(), rMoveDn);
                m.setTake();
                availableMoves.add(m);
            }
        }else if (c.getColour() == Colour.RED){
            //up left/right
            for(Checker piece : pieces){
                if(piece.getPos().x == lMoveUp.x && piece.getPos().y == lMoveUp.y && c.isAlive()){   //if location is occupied
                    lUp = false;                                                                     //set position to false 
                    if(!piece.getColour().equals(c.getColour())){   //check to see if blocking piece is from other team
                        print("break0.5");
                        takeL = true;
                        for(Checker piece2 : pieces){
                            if((piece2.getPos().x == (lMoveUp.x - 1) && piece2.getPos().y == (lMoveUp.y + 1)) && piece2.isAlive()){    
                                //no free space, cannot take
                                takeL = false; 
                                //print("break1");
                                break;      //exit the if statment as no take possible 
                            }else{          //TO DO remove this else as no longer used?
                                //free space behind, take available
                                //print("break2.5");
                                takeL = true;         //signal that a piece can be taken, 
                            }
                        }
                    }
                }else if(piece.getPos().x == rMoveUp.x && piece.getPos().y == rMoveUp.y && c.isAlive()){
                    rUp = false; 
                    if(!piece.getColour().equals(c.getColour())){   //check to see if blocking piece is from other team
                        takeR = true;                         
                        for(Checker piece2 : pieces){
                            if(piece2.getPos().x == rMoveUp.x+1 && piece2.getPos().y == rMoveUp.y+1 && piece2.isAlive()){    
                                //no free space, cannot take
                                takeR = false; 
                                break;
                            }else{
                                //free space behind, take available
                                takeR = true;         //signal that a piece can be taken, 
                            }
                        }
                    }
                }  
            }
            if(lUp){
                Move m = new Move(c.getPos(), lMoveUp);
                availableMoves.add(m);
            }else if(takeL == true) {           
                lMoveUp.x -=1;
                lMoveUp.y +=1;
                Move m = new Move(c.getPos(), lMoveUp);
                m.setTake();
                availableMoves.add(m);  //TO DO add this line elsewhere king
                //print(m.getEnd().toString());
                //print("break3");
            }     

            if(rUp){
                Move m = new Move(c.getPos(), rMoveUp);
                availableMoves.add(m);
            }else if(takeR == true) {           
                rMoveUp.x +=1;
                rMoveUp.y +=1;
                Move m = new Move(c.getPos(), rMoveUp);
                m.setTake();
                availableMoves.add(m);
            }
        }
        
        //quick hack to check that all available moves are valid positions
        ArrayList<Move> availableMov = new ArrayList<>();
        for(Position pos:validPos){
            for(Move mov:availableMoves){
                if(pos.x == mov.getEnd().x && pos.y == mov.getEnd().y){
                    availableMov.add(mov);
                }
            }
        }
        
        //hack to make sure that if a capture move exists, only capture moves are returned
        ArrayList<Move> availableMovCap = new ArrayList<>();
        for(Move m : availableMov){
            if(m.getTake()==true){
                availableMovCap.add(m);
            }
        }
        
        if(availableMovCap.size() > 0){ //there are capture moves available, return only those moves
            return(availableMovCap);
        }else{
            return (availableMov);
        }
    }
    
    //TO DO makework!
    public boolean gameOver(Colour turn){
        getAllAvailableMoves(turn);
        if(allMoves.isEmpty()){
            return true;
        }
        return false;
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
