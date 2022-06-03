package com.mygdx.game.movement;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entity.EnemyShip;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.Projectile;
import com.sun.tools.javac.util.Pair;

import java.util.Iterator;

public class Collisions {
    Array<Entity> group1;
    Array<Entity> group2;


    public Collisions(Array<Entity> group1, Array<Entity> group2) {
        this.group1 = group1;
        this.group2 = group2;
    }

    public Array<Pair<Entity, Entity>> scanForCollisions() {
        Array<Pair<Entity, Entity>> out = new Array<Pair<Entity, Entity>>();
        for (Entity obj1 : group1) {
            for (Entity obj2 : group2) {
                if (obj1.getRect().overlaps(obj2.getRect())) {
                    Pair<Entity, Entity> pair = new Pair<Entity, Entity>(obj1, obj2);
                    if (!out.contains(pair, true))
                        out.add(pair);
                }
            }
        }
        return out;
    }
}
