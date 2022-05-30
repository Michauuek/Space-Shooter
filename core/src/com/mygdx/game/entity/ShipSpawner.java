package com.mygdx.game.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class ShipSpawner {

    private long timeSpawn;
    private Array<EnemyShip> enemyShips;
    private Array<Projectile> enemyProjectiles;
    private int screenWidth, screenHeight;

    public ShipSpawner(Array<EnemyShip> enemyShips, Array<Projectile> enemyProjectiles, int screenWidth, int screenHeight) {
        this.timeSpawn = TimeUtils.millis();
        this.enemyShips = enemyShips;
        this.enemyProjectiles = enemyProjectiles;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void generate(){
        if(TimeUtils.millis() - timeSpawn > 999)
        {
            enemyShips.add(new EnemyShip(MathUtils.random(0, screenWidth - 10), screenHeight,enemyProjectiles));
            timeSpawn = TimeUtils.millis();
        }
    }
}
