package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.movement.PlayerMovement;

public class Player extends Ship {

    PlayerMovement playerMovement;
    private long timeShot;

    private Texture projectileTexture;

    public Player(int posX, int posY) {
        this.texture = new Texture(Gdx.files.internal("shipPlayer.png"));
        this.projectileTexture = new Texture(Gdx.files.internal("blasterbolt.png"));
        this.rectangle = new Rectangle(posX,posY,10,15);

        playerMovement = new PlayerMovement(rectangle);
        timeShot = TimeUtils.millis();
    }

    public PlayerMovement getPlayerMovement() {
        return playerMovement;
    }

    @Override
    public void shoot(){
        if(TimeUtils.millis() - timeShot > 300)
        {
            PlayerProjectile playerProjectile = new PlayerProjectile(2,
                    rectangle.x + rectangle.width/2- 1.5f,
                    rectangle.y + rectangle.height -2,
                    70,
                    projectileTexture);

            EntitiesManager.registerEntity(playerProjectile);
            timeShot = TimeUtils.millis();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture,rectangle.x,rectangle.y,rectangle.width,rectangle.height);
    }

    @Override
    public void checkIfDestroy() {

    }

    @Override
    public void update(float delta) {
        shoot();
    }
}
