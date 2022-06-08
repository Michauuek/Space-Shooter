package com.mygdx.game.repo;

import com.mygdx.game.entity.Entity;

public interface ICollisionEvent {
    void onCollisionBetween(Entity e1, Entity e2);
}
