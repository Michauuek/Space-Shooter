package com.mygdx.game.entity;


import java.io.Serializable;

public class Score implements Serializable {

    private Long points;


    public Score() {
        this.points = 0L;
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
