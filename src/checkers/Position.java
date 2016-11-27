/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

/**
 *
 * @author Simon
 */
public class Position {
    int x,y;
    Position(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    @Override
    public String toString(){
        return("[" + (x+1) + "," + (y+1) + "]");
    }
}
