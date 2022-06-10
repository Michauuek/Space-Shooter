package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Manages all entities in game.
 * Once you register entity it will show up on the screen.
 */
public class EntitiesManager {

    private static Map<String,ArrayList<Entity>> map;

    public EntitiesManager(){
        map = new HashMap<>();
    }

    /**
     * Register entity to map
     * @param e Entity to register
     */
    public static void registerEntity(Entity e){
        String entityName = e.getClass().getSimpleName();
       ArrayList<Entity> array = map.get(entityName);
       if(array == null){
           array = new ArrayList<Entity>();
           map.put(entityName,array);
       }
       array.add(e);
    }

    /**
     * Unregister entity
     * @param e Entity to unregister
     */
    public static void unregisterEntity(Entity e){
        // Exepction
        String entityName = e.getClass().getSimpleName();
        ArrayList<Entity> array = map.get(entityName);

        array.remove(e);
    }

    /**
     *
     * @param entityName Determine what type of ArrayList should be returned
     * @return Returns ArrarList of entities
     */
    public static ArrayList<Entity> getArray(String entityName){
        ArrayList<Entity> array = map.get(entityName);
        return array;
    }

    /**
     * Updates all entites in every ArrayList
     * @param delta
     * @param batch
     */
    public void updateAllEntities(float delta, SpriteBatch batch) {

        for(int i=map.keySet().size()-1;i >= 0; i--){

           String key = map.keySet().toArray()[i].toString();

           ArrayList<Entity> array = map.get(key);

           for(int j=array.size()-1;j>=0;j--){
               Entity e = array.get(j);
               e.render(batch);
               e.update(delta);
               e.checkIfDestroy();
           }
        }
    }
}
