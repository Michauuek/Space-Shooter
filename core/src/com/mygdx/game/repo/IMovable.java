package com.mygdx.game.repo;

import com.badlogic.gdx.math.Vector2;

public interface IMovable {

     /**
      * moves in a direction specified by vector
      * @param vector direction of move
      * @param delta delta time
      */
     void move(Vector2 vector, float delta);

     /**
      * moves to a cartain spot on screen
      * @param vector vector of position
      * @param delta delta time
      */
     void moveTo(Vector2 vector, float delta);

}
