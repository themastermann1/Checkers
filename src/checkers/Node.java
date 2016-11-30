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
public class Node {
    Node parent;
    ArrayList<Node> children;
    Move move;
    int Score;
    
    Node(Node p, Move m){
        parent = p;
        children = new ArrayList<>();
        move = m;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }
    
    public void addChild(Node n){
        this.children.add(n);
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }
    
}
