package com.mygdx.game.entity;


import java.io.Serializable;

/**
 * Class to store and manage score of current game
 */
public class Score implements Serializable {

    private Long points;


    public Score() {
        this.points = 0L;
    }

    public Score(Long points) {
        this.points = points;
    }

    public void addPoints(){
        points += 100L;
    }


    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }
}
