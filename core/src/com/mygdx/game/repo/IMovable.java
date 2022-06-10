package com.mygdx.game.repo;

import com.badlogic.gdx.math.Vector2;

public interface IMovable {


     void move(Vector2 vector, float delta);
     void moveTo(Vector2 vector, float delta);

}
