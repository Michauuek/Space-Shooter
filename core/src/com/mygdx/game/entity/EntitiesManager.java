package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntitiesManager {

    private static Map<String,ArrayList<Entity>> map;

    public EntitiesManager(){
        map = new HashMap<>();
    }

    public static void RegisterEntity(Entity e){
        String entityName = e.getClass().getSimpleName();
       ArrayList<Entity> array = map.get(entityName);
       if(array == null){
           array = new ArrayList<Entity>();
           map.put(entityName,array);
       }
       array.add(e);
    }

    public static void unregisterEntity(Entity e){
        // Exepction
        String entityName = e.getClass().getSimpleName();
        ArrayList<Entity> array = map.get(entityName);

        array.remove(e);
    }

    public static ArrayList<Entity> getArray(String entityName){
        ArrayList<Entity> array = map.get(entityName);
        return array;
    }

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
