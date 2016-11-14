/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

/**
 *
 * @author sdm29
 */
public class Checker {
    int ID;
    Colour colour;
    Type rank;
    int xPos;
    int yPos;
    boolean alive;
    
    Checker(Colour team, int x, int y){
        xPos = x;
        yPos = y;
        colour = team;
        rank = Type.PLEB;
        alive = true;
    }
    
    public void move(int newX, int newY){
        xPos = newX;
        yPos = newY;
    }
    
    public void kill(){
        alive = false;
    }
    
    public void king(){
        rank = Type.KING;
    } 
    
    public boolean isAlive() {
        return alive;
    }

    public Colour getColour() {
        return colour;
    }

    public Type getRank() {
        return rank;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
}
