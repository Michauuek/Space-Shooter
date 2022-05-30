package com.mygdx.game.repo;

import com.badlogic.gdx.math.Vector2;

public interface IMovable {
     void moveLeft(float delta);
     void moveRight(float delta);

     void move(Vector2 vector, float delta);

     public void setPosition(float x, float y);
}
