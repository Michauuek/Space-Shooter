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
    private Array<Projectile> playerProjectiles;

    private Texture projectileTexture;

    public Player(int posX, int posY, int screenWidth, int screenHeight, Array<Projectile> playerProjectiles) {
        this.texture = new Texture(Gdx.files.internal("shipPlayer.png"));
        this.projectileTexture = new Texture(Gdx.files.internal("blasterbolt.png"));
        this.rectangle = new Rectangle(posX,posY,10,15);

        playerMovement = new PlayerMovement(rectangle,screenWidth, screenHeight);
        timeShot = TimeUtils.millis();
        this.playerProjectiles = playerProjectiles;
    }

    public PlayerMovement getPlayerMovement() {
        return playerMovement;
    }

    @Override
    public void shoot(){
        if(TimeUtils.millis() - timeShot > 300)
        {
            playerProjectiles.add(new Projectile(2,
                    rectangle.x + rectangle.width/2- 1.5f,
                    rectangle.y + rectangle.height -2,
                    70,
                    projectileTexture));
            timeShot = TimeUtils.millis();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture,rectangle.x,rectangle.y,rectangle.width,rectangle.height);
    }



    @Override
    public void update(float delta) {
        shoot();
        //System.out.println(TimeUtils.millis());
    }
}
