package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.screen.GameScreen;

public class PlayerProjectile extends Projectile{
    public PlayerProjectile(float damage, float x, float y, float speedY, Texture texture) {
        super(damage, x, y, speedY, texture);
    }

    @Override
    public void checkIfDestroy() {
        if (getRect().y > GameScreen.WORLD_HEIGHT) {
            EntitiesManager.UnregisterEntity(this);
        }
    }
}
