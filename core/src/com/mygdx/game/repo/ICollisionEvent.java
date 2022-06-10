package com.mygdx.game.repo;

import com.mygdx.game.entity.Entity;

/**
 * Describes what should happen when Entity1 collides with Entity2.
 */
public interface ICollisionEvent {
    void onCollisionBetween(Entity e1, Entity e2);
}
