package com.mygdx.game.entity;

import com.mygdx.game.repo.Collision;
import com.mygdx.game.repo.CollisionsRegistry;
import com.mygdx.game.repo.ICollisionEvent;

import java.util.ArrayList;

public class CollisonManager {

    private CollisionsRegistry collisionsRegistry;

    public void Bind(CollisionsRegistry collisionsRegistry){
        this.collisionsRegistry = collisionsRegistry;
    }

    public void checkAllCollison(){
        for (Collision c: collisionsRegistry.getCollisions()) {
            checkCollisionBetween(c);
        }
    }

    private void checkCollisionBetween(Collision collision){

        ArrayList<Entity> e1 = EntitiesManager.getArray(collision.getFirstEntity());
        ArrayList<Entity> e2 = EntitiesManager.getArray(collision.getSecondEntity());

        if(e1 != null && e2 != null) {
            for (int i = e1.size() - 1; i >= 0; i--) {
                for (int j = e2.size() - 1; j >= 0; j--) {
                    if (e2.get(j).collides(e1.get(i))) {
                        collision.getEvent().onCollisionBetween(e1.get(i),e2.get(j));
                    }
                }
            }
        }
    }
}
