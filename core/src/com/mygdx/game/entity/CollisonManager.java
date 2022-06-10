package com.mygdx.game.entity;

import com.mygdx.game.repo.Collision;

import java.util.ArrayList;

public class CollisonManager {

    class Pair<T, V>
    {
        public Pair(T first, V second) {
            this.first = first;
            this.second = second;
        }

        public T first;
        public V second;
    }

    private ICollisionProvider collisionProvider;

    public void Bind(ICollisionProvider collisionProvider){
        this.collisionProvider = collisionProvider;
    }

    /**
     * Check all collision types from collisonRegistry
     */
    public void checkAllCollison(){
        for (Collision c: collisionProvider.getCollisions()) {
            checkCollisionBetween(c);
        }
    }

    /**
     * Check if given collison type occurs
     * @param collision
     */
    private void checkCollisionBetween(Collision collision){

        ArrayList<Entity> e1 = EntitiesManager.getArray(collision.getFirstEntity());
        ArrayList<Entity> e2 = EntitiesManager.getArray(collision.getSecondEntity());

        ArrayList<Pair<Entity,Entity>> valuesToRemove = new ArrayList<>();

        if(e1 != null && e2 != null) {
            for (int i = e1.size() - 1; i >= 0; i--) {
                for (int j = e2.size() - 1; j >= 0; j--) {
                    if (e2.get(j).collides(e1.get(i))) {
                        valuesToRemove.add(new Pair<>(e1.get(i),e2.get(j)));
                    }
                }
            }
            for(Pair<Entity,Entity> pair : valuesToRemove){
                collision.getEvent().onCollisionBetween(pair.first, pair.second);
            }
        }
    }
}
