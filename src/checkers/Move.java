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
public class Move {
    Position start;
    Position end;
    Boolean take;
    int score;
    
    Move(Position s, Position e){
        start = s;
        end = e;
        take = false;
        score = 0;
    }

    public Position getStart() {
        return start;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public Position getEnd() {
        return end;
    }

    public void setEnd(Position end) {
        this.end = end;
    }

    public Boolean getTake() {
        return take;
    }

    public void setTake() {
        this.take = true;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
}
