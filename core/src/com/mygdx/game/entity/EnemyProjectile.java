package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;

public class EnemyProjectile extends Projectile{

    public EnemyProjectile(float damage, float x, float y, float speedY, Texture texture) {
        super(damage, x, y, speedY, texture);
    }

    @Override
    public void checkIfDestroy() {
        if (getRect().y + getRect().height  < 0) {
            EntitiesManager.unregisterEntity(this);
        }
    }
}
