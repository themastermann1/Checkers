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
    Position pos;
    boolean alive;
    
    Checker(Colour team, Position p){
        pos = p;
        colour = team;
        rank = Type.PLEB;
        alive = true;
    }
    
    public void move(Position newP){
        this.pos = newP;
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

    public Position getPos() {
        return pos;
    }
}
