package com.mygdx.game.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.repo.IMovable;

public class InputHandler {

    IMovable movement;

    Camera camera;
    Vector2 vector;

    public InputHandler(IMovable movement, Camera camera){

        this.movement = movement;
        this.camera = camera;
        vector = new Vector2();
    }

    public void ListenForInput(float delta){
        vector.x = 0;
        vector.y = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            //movement.moveLeft(delta);
            vector.x += -1;
            //movement.move(-1.f,0.f, delta);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            //movement.moveRight(delta);
            vector.x += 1;
            //movement.move(1.f,0.f, delta);
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            //movement.moveLeft(delta);
            vector.y += 1;
            //movement.move(0.f,1.f, delta);
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            //movement.moveRight(delta);
            vector.y += -1;
            //movement.move(0.f,-1.f, delta);

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);


            camera.unproject(touchPos);
            movement.setPosition(touchPos.x, touchPos.y);
            //bucket.x = touchPos.x - 64 / 2;
        }

        movement.move(vector, delta);
    }
}
