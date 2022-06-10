package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Class to manage player's lives
 */
public class Lives {
    private int count;
    private Texture texture;


    public Lives() {
        this.count = 3;
        this.texture = new Texture(Gdx.files.internal("heart.png"));
    }

    public void render(SpriteBatch batch, float delta, int width, int height){

        for(int i = 0; i < count; i++){
            batch.draw(texture, width + i*7, height, 7,7);
        }
    }

    /**
     * Function check if own a lives
     * @return true if game isOver
     */
    public Boolean isOver(){
        if(count == 0)
            return true;

        return false;
    }

    public void loseLife(){
        count -= 1;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
