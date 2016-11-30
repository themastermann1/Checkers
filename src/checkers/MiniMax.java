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
    Player player;
    int maxDepth;
    MiniMax(Board b, Player p){
        this.board = b;
        this.diff = p.getDiff();
        this.player = p;
        clone = new Board();
        clone.setPieces(board.getPieces());//really hope this wont change the positions in the original board
        //clone.getAllAvailableMoves(player.getTeam());
        
        maxDepth = diff.getDepth();
        
        evalBoard(p);
    }
    
    public void evalBoard(Player p){
        miniMax(0, p, -99999, 99999);
    }
    
    //minimax evaluation
    public int miniMax(int depth, Player p, int a, int b){
        int topScore;

        if(depth > maxDepth){
            return(0);
        }
        
        //get another player thats the otehr team as the minimax target
        //this payer does not exist, and will never make any move, 
        //but is required to work out the "best" move to oppose the move chosen for
        //the first player, so that the first player can minimise this.
        Player p2 = new Player();
        if(p.getTeam() == Colour.RED){
            p2.setTeam(Colour.BLACK);
            topScore = -1;
        }else{
            p2.setTeam(Colour.RED);
            topScore = 3;
        }
        
        //change back to a list?
        //Node start = new Node(null, null);
        ArrayList<Integer> scores = new ArrayList<>();  
        board.getAllAvailableMoves(p.getTeam());
        //if the game is won on this level, return a high score (10 or something)
        if(board.allMoves.isEmpty()){
            if(p.getTeam()==Colour.BLACK){
                return(-1); 
            }else if(p.getTeam()==Colour.RED){
                return(3);
            }
        }
        
        for(Move m : clone.allMoves){
            if(p.getTeam() == Colour.RED){              //red teams go
                //make the first move in the list
                board.movePiece(m, p.getTeam());
                int currentScore = miniMax(depth +1, p2, a, b); //increment and use other player
                if(currentScore > topScore){
                    topScore = currentScore;
                }
                if(currentScore > a){
                    a = currentScore;
                }
                if(depth == 0){     //at the root of the minimax
                    //make a list of moves
                    m.setScore(currentScore);
                    //listofmoves.add(m);
                }
                //get the value of the previous move     
                //add the score for that chain of moves
            }else if(p.getTeam() == Colour.BLACK){      //black teams go
                board.movePiece(m, p.getTeam());
                int currentScore = miniMax(depth +1, p2, a, b);
                if(currentScore < topScore){
                    topScore = currentScore;
                }
                if(currentScore < b){
                    b = currentScore;
                }
            }
            //make sure these moves are not on real board...
            
            //prune bad leafs
            if(a >= b){
                break;
            }
        }
        return(topScore);
    }
}
