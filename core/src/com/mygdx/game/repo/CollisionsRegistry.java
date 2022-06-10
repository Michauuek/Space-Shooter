package com.mygdx.game.repo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entity.*;

import java.util.ArrayList;

public class CollisionsRegistry implements ICollisionProvider {

    private ArrayList<Collision> collisions;

    private Score score;
    private Lives lives;

    public void Bind(Score score){
        this.score = score;
    }

    public void Bind(Lives lives){
        this.lives = lives;
    }

    public CollisionsRegistry(){
        collisions = new ArrayList<>();
        InitializeCollisions();
    }

    public ArrayList<Collision> getCollisions() {
        return collisions;
    }

    public void InitializeCollisions(){
        collisions.add(new Collision(PlayerProjectile.class.getSimpleName(),
                EnemyShip.class.getSimpleName(),
                new ICollisionEvent() {
                    @Override
                    public void onCollisionBetween(Entity e1, Entity e2) {
                        EntitiesManager.unregisterEntity(e1);
                        EntitiesManager.unregisterEntity(e2);
                        Explosion explosion =
                                new Explosion(
                                        e2.getRect().x + e2.getRect().width/2,
                                        e2.getRect().y + e2.getRect().height/2,
                                        new Texture(Gdx.files.internal("exp2_0.png")),
                                        4,
                                        4
                                );
                        EntitiesManager.registerEntity(explosion);
                        score.addPoints();
                    }
                }));
        collisions.add(new Collision(Player.class.getSimpleName(),
                EnemyProjectile.class.getSimpleName(),
                new ICollisionEvent() {
                    @Override
                    public void onCollisionBetween(Entity e1, Entity e2) {
                        EntitiesManager.unregisterEntity(e2);
                        lives.loseLife();
                    }
                }));
    }
}
