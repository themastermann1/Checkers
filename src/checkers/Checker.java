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
        this.alive = false;
        //System.out.println("R.I.P");
    }
    
    //IT LIVES, IT LIVES!, ITS ALLLLLLLIVE!!!!!
    public void ressurect(){
        this.alive = true;
    }
    
    public void king(){
        rank = Type.KING;
        //System.out.println("your piece just became a king!");
    } 
    
    public boolean isAlive() {
        return alive;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Type getRank() {
        return rank;
    }

    public void setRank(Type rank) {
        this.rank = rank;
    }

    public Position getPos() {
        return pos;
    }
}
