package com.mygdx.game.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.screen.GameScreen;

public class ShipSpawner {

    private long timeSpawn;

    public ShipSpawner() {
        this.timeSpawn = TimeUtils.millis();
    }

    public void generate(){
        if(TimeUtils.millis() - timeSpawn > 1200)
        {
            EnemyShip newEnemyShip = new EnemyShip(MathUtils.random(0, GameScreen.WORLD_WIDTH - 10), GameScreen.WORLD_HEIGHT);
            EntitiesManager.registerEntity(newEnemyShip);
            timeSpawn = TimeUtils.millis();
        }
    }
}
