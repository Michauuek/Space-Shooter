package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.movement.PlayerMovement;

public class EnemyShip extends Ship{

    private long timeShot;
    private Array<Projectile> enemyProjectiles;

    private Texture projectileTexture;

    public EnemyShip(int posX, int posY, Array<Projectile> enemyProjectiles) {
        this.texture = new Texture(Gdx.files.internal("DurrrSpaceShip.png"));
        this.projectileTexture = new Texture(Gdx.files.internal("enemy_blasterbolt.png"));
        this.rectangle = new Rectangle(posX,posY,6,10);

        timeShot = TimeUtils.millis();
        this.enemyProjectiles = enemyProjectiles;
    }

    @Override
    public void update(float delta) {
        shoot();
        rectangle.y -= 10*delta;

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture,rectangle.x,rectangle.y,rectangle.width,rectangle.height, 0,0,texture.getWidth(),texture.getHeight(),false,true);
    }

    @Override
    public void shoot() {
        if(TimeUtils.millis() - timeShot > 2500)
        {
            enemyProjectiles.add(new Projectile(2,
                    rectangle.x + rectangle.width/2- 1.5f,
                    rectangle.y ,
                    -25,
                    projectileTexture));
            timeShot = TimeUtils.millis();
        }
    }




}
