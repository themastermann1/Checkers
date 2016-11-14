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
    private ArrayList<Checker> board;
    private int[] even = {1,2,5,7};
    private int[] odd = {0,2,4,6};
    int[][] validPos;
    //number of pieces on each team
    int teamCnt;
    public void Board(){
        board = new ArrayList<>();
        teamCnt = 12;
        for(int c=0; c<32; c++){
            for(int i=0; i<8; i++){
                for(int j=0; j<4; j++){
                    validPos[c][0] = even[j];
                    validPos[c][1] = i;
                }
                for(int j=0; j<4; j++){
                    validPos[c][0] = odd[j];
                    validPos[c][1] = i;
                }
            }
        }
        System.out.println(validPos);
    }
    

    //initilise the pieces for the black team
    public void initBlack(){
        // put a black piece in the first teamCnt valid postions
        for(int i=0; i<teamCnt; i++){
            Checker c = new Checker(Colour.BLACK,validPos[i][0],validPos[i][1]);
            board.add(c);
        }
    }
    
    public void initRed(){
        // put a red piece in the last teamCnt valid postions
         for(int i=31; (32-teamCnt)>i; i--){
            Checker c = new Checker(Colour.BLACK,validPos[i][0],validPos[i][1]);
            board.add(c);
         }
    }
    
    
    
}
