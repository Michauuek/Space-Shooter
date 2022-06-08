package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class EnemyShip extends Ship{

    private long timeShot;
    private Texture projectileTexture;
    private static Lives lives;

    public EnemyShip(int posX, int posY) {
        this.texture = new Texture(Gdx.files.internal("DurrrSpaceShip.png"));
        this.projectileTexture = new Texture(Gdx.files.internal("enemy_blasterbolt.png"));
        this.rectangle = new Rectangle(posX,posY,6,10);

        timeShot = TimeUtils.millis();
    }

    public static void Bind(Lives lives) {
        EnemyShip.lives = lives;
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
    public void checkIfDestroy() {
        if (getRect().y + getRect().height  < 0){
            lives.loseLife();
            EntitiesManager.unregisterEntity(this);
        }
    }

    @Override
    public void shoot() {
        if(TimeUtils.millis() - timeShot > 2500)
        {
            EnemyProjectile enemyProjectile = new EnemyProjectile(2,
                    rectangle.x + rectangle.width/2- 1.5f,
                    rectangle.y ,
                    -25,
                    projectileTexture);

            EntitiesManager.registerEntity(enemyProjectile);
            timeShot = TimeUtils.millis();
        }
    }
}
