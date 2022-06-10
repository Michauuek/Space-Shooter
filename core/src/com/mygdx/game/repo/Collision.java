package com.mygdx.game.repo;

/**
 * Contain information about collision that occur between two classes.
 */
public class Collision {
    private String firstEntity;
    private String secondEntity;
    private ICollisionEvent event;

    public Collision(String firstEntity,String secondEntity,ICollisionEvent event){
        this.firstEntity =firstEntity;
        this.secondEntity = secondEntity;
        this.event = event;
    }

    public String getSecondEntity() {
        return secondEntity;
    }

    public String getFirstEntity() {
        return firstEntity;
    }

    public ICollisionEvent getEvent() {
        return event;
    }
}
