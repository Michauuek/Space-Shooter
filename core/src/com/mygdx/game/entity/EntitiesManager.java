package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class EntitiesManager {

    private static ArrayList<Entity> entities;

    public EntitiesManager(){
        entities = new ArrayList<>();
    }

    public static void RegisterEntity(Entity e){
        entities.add(e);
    }

    public static void UnregisterEntity(Entity e){
        entities.remove(e);
    }

    public void UpdateAllEntities(float delta, SpriteBatch batch) {
        for (int i = entities.size()-1; i >= 0; i--) {
            Entity e = entities.get(i);
            e.render(batch);
            e.update(delta);
            e.checkIfDestroy();
        }
    }
}
