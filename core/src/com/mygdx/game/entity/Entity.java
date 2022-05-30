package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {

    protected Texture texture;
    protected Rectangle rectangle;

//    public Entity(Texture texture, Rectangle rectangle) {
//        this.texture = texture;
//        this.rectangle = rectangle;
//    }

    public abstract void update(float delta);

    public abstract void render(SpriteBatch batch);

    public Rectangle getRect(){
        return rectangle;
    }
}
