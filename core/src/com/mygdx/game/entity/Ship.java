package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Ship extends Entity{

    //public abstract void move()

    public abstract void shoot();

//    public boolean intersects(Rectangle rectangle){
//        return this.rectangle.overlaps(rectangle);
//    }

}
