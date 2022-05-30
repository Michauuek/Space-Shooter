package com.mygdx.game.movement;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.repo.IMovable;

public class PlayerMovement implements IMovable {

    private float horizontalSpeed = 60;
//    private float positionX;
//    private int playerWidth;

    Rectangle playerRectangle;
    private int screenWidth;
    private int screenHeight;

    public PlayerMovement(Rectangle playerRectangle, int screenWidth, int screenHeight){

        this.playerRectangle = playerRectangle;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void moveLeft(float delta) {
        playerRectangle.x -= horizontalSpeed * delta;
        CheckBounds();
    }
    public void moveRight(float delta){
        playerRectangle.x += horizontalSpeed * delta;
        CheckBounds();
    }

    public void move(Vector2 vector, float delta){
        //to limit the speed when we move in two axis
        vector.limit(1.f);
        playerRectangle.x += vector.x * delta * horizontalSpeed;
        playerRectangle.y += vector.y * delta * horizontalSpeed;
        CheckBounds();
    }

//    public float getPositionX() {
//        return positionX;
//    }

    private void CheckBounds(){
        if(playerRectangle.x > screenWidth-playerRectangle.width)
            playerRectangle.x = screenWidth-playerRectangle.width;
        if(playerRectangle.x < 0)
            playerRectangle.x = 0;

        if(playerRectangle.y > screenHeight-playerRectangle.height)
            playerRectangle.y = screenHeight-playerRectangle.height;
        if(playerRectangle.y < 0)
            playerRectangle.y = 0;
    }

    public void setPosition(float x, float y){
        playerRectangle.x = x - playerRectangle.width/2;
        playerRectangle.y = y - playerRectangle.height/2;

    }
}
