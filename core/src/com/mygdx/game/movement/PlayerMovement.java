package com.mygdx.game.movement;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.repo.IMovable;
import com.mygdx.game.screen.GameScreen;

public class PlayerMovement implements IMovable {

    private float horizontalSpeed = 60;
    Rectangle playerRectangle;

    public PlayerMovement(Rectangle playerRectangle){

        this.playerRectangle = playerRectangle;
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

    public void moveTo(Vector2 vector, float delta){

        vector.x -=playerRectangle.x + playerRectangle.width/2;
        vector.y -=playerRectangle.y + playerRectangle.height/2;
        move(vector, delta);
    }

//    public float getPositionX() {
//        return positionX;
//    }

    private void CheckBounds(){
        if(playerRectangle.x > GameScreen.WORLD_WIDTH -playerRectangle.width)
            playerRectangle.x = GameScreen.WORLD_WIDTH-playerRectangle.width;
        if(playerRectangle.x < 0)
            playerRectangle.x = 0;

        if(playerRectangle.y > GameScreen.WORLD_HEIGHT-playerRectangle.height)
            playerRectangle.y = GameScreen.WORLD_HEIGHT-playerRectangle.height;
        if(playerRectangle.y < 0)
            playerRectangle.y = 0;
    }

    public void setPosition(float x, float y){
        playerRectangle.x = x - playerRectangle.width/2;
        playerRectangle.y = y - playerRectangle.height/2;

    }
}
