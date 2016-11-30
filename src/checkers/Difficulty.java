/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

/**
 *
 * @author sdm29
 */
public enum Difficulty {
    BABY (0),
    EASY (1),
    HARD (3),
    SUICIDAL (10),
    HELL_ON_EARTH (15);

    private final int depth;
    
    Difficulty(int depth) {
        this.depth = depth;
    }

    public int getDepth(){
        return depth;
    }
}