package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Projectile extends Entity{

    private float damage;
    private float speedY;



    public Projectile(float damage, float x, float y, float speedY, Texture texture) {
        this.damage = damage;
        this.texture = texture;
        rectangle = new Rectangle(x,y,3,7);
        this.speedY = speedY;
    }



    @Override
    public void update(float delta) {
        rectangle.y += speedY * delta;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture,rectangle.x,rectangle.y,rectangle.width,rectangle.height);
    }


}
