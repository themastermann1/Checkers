/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.util.ArrayList;

/**
 *
 * @author Simon
 */
public class MiniMax {
    //class to handle the minimax algorithm as well as alpha beta pruning
    Difficulty diff;
    Board board;
    Board clone;
    Board clone2;
    Player player;
    int maxDepth;
    ArrayList<Move> successorEvals = new ArrayList<>();
    //absoloutly terrible - remove as soon as undo move implemented
    ArrayList<Board> previousBoards = new ArrayList<>();
    
    MiniMax(Board b, Player p){
        this.board = b;
        this.diff = p.getDiff();
        this.player = p;
        clone = new Board();
        clone.setPieces(board.getPieces());//really hope this wont change the positions in the original board
        clone.getAllAvailableMoves(player.getTeam());
        clone2 = new Board();
        clone2.setPieces(clone.getPieces());
        clone2.getAllAvailableMoves(player.getTeam());
        
        switch (diff) {
            case BABY:
                maxDepth = 1;
                break;                    
            case EASY:
                maxDepth = 3;
                break;        
            case HARD: 
                maxDepth = 5;
                break;   
            case SUICIDAL:
                maxDepth = 10;
                break;                    
            case HELL_ON_EARTH:
                maxDepth = 20;
                break;     
            default:
                maxDepth = 5;
                break;
        }
        
        System.out.println("max depth" + maxDepth);
    }
    
    public int evalBoard(){
        int i = miniMax(0, player, -99999, 99999);
        return i;
    }
    
    //minimax evaluation
    public int miniMax(int depth, Player p, int a, int b){
        int topScore;
        System.out.println("current depth" + depth);
        //get another player thats the other team as the minimax target
        //this payer does not exist, and will never make any move, 
        //but is required to work out the "best" move to oppose the move chosen for
        //the first player, so that the first player can minimise this.
        Player p2 = new Player();
        if(p.getTeam() == Colour.RED){
            p2.setTeam(Colour.BLACK);
        }else{
            p2.setTeam(Colour.RED);
        }
        
        //set the worst possible best score, low if its the player the mini max was called on, high otherwise
        if(p.getTeam() == player.getTeam()){
            topScore = -1;
        }else{
            topScore = 1;
        }
        
        clone.getAllAvailableMoves(p.getTeam());
        System.out.println("number of moves" + board.allMoves.size());
        //if the game is won on this level, return a high score (10 or something)
        if(clone.allMoves.isEmpty()){
            if(p.getTeam()==player.getTeam()){  //loosing team = caling team, ive shitty score
                return(-1); 
            }else if (!(p.getTeam()==player.getTeam())){
                return(1);                      //loosing team = opposing team, good score
            }else{
                return(0);                      //idk, draws arnt possible, but return a neutral score
            }
        }
        
        if(depth > maxDepth){       //end of evaluations, return a neutral score
            System.out.println("depth reached");
            return(0);
        }
        System.out.println("break");
        for(Move m : clone.allMoves){
            clone.getAllAvailableMoves(p.getTeam());
            System.out.println("break1");
            if(p.getTeam() == Colour.RED){      
                System.out.println("break2");//red teams go
                //make the first move in the list
                System.out.println(m.getStart().toString() + m.getEnd().toString());
                clone.movePiece(m, p.getTeam());
                clone.displayBoard();
                System.out.println("break3");
                int currentScore = miniMax(depth +1, p2, a, b); //increment and use other player
                if(currentScore > topScore){
                    topScore = currentScore;
                }
                if(currentScore > a){
                    a = currentScore;
                }
                System.out.println("break4 current score" + currentScore);
                System.out.println("curent depth2 " + depth);
                if(depth == 0){     //at the root of the minimax
                    //make a list of moves
                    m.setScore(currentScore);
                    System.out.println("scorezz" + currentScore);
                    successorEvals.add(m);
                }
                //get the value of the previous move     
                //add the score for that chain of moves
            }else if(p.getTeam() == Colour.BLACK){      //black teams go
                clone.movePiece(m, p.getTeam());
                int currentScore = miniMax(depth +1, p2, a, b);
                if(currentScore < topScore){
                    topScore = currentScore;
                }
                if(currentScore < b){
                    b = currentScore;
                }
            }
            //make sure these moves are not on real board...
            //reset the last move made
            clone.updateBoard();
            clone.displayBoard();
            clone.undoMove(m, p.getTeam());
            clone.updateBoard();
            clone.displayBoard();
            //prune bad leafs
            if(a >= b){
                break;
            }
        }
        return(topScore);
    }
    
    public Move getBestMove(){
        int max = -999;
        Move bestMove = new Move(null, null);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        if(successorEvals.isEmpty()){
            System.out.print("shits empty yo");
        }
        for(Move mov: successorEvals){
            if(max < mov.getScore()){
                max=mov.getScore();
                bestMove = mov;
                System.out.print("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            }
        }
        //System.out.print("THE BEST MOVE IS: " + bestMove.getStart().toString() + bestMove.getEnd().toString());
        return bestMove;
    }
    
    public void resetClone(){
        this.clone = this.clone2;
    }
}
