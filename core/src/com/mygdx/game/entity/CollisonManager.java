package com.mygdx.game.entity;

import java.util.ArrayList;

public class CollisonManager {

    public void checkAllCollison(){

        checkPlayerProjectilesCollison();
    }

    private void checkPlayerProjectilesCollison(){
        ArrayList<Entity> playerProjectiles = EntitiesManager.getArray(PlayerProjectile.class.getSimpleName());
        ArrayList<Entity> enemyShips = EntitiesManager.getArray(EnemyShip.class.getSimpleName());

        if(playerProjectiles != null && enemyShips != null) {

            for (int i = playerProjectiles.size() - 1; i >= 0; i--) {
                for (int j = enemyShips.size() - 1; j >= 0; j--) {
                    if (enemyShips.get(j).collides(playerProjectiles.get(i))) {
                          EntitiesManager.unregisterEntity(enemyShips.get(j));
                    }
                }
            }
        }
    }
}
