package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import javax.sql.rowset.FilteredRowSet;

public class Explosion extends Entity {


    private float x;
    private float y;
    private Texture texture;
    private int columns;
    private int rows;
    private long time;
    private boolean isOver;

    private TextureRegion[] textures;
    private int currentTexture;
    private int width;
    private int height;


    public Explosion(float x, float y, Texture texture, int columns, int rows) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.columns = columns;
        this.rows = rows;
        textures = new TextureRegion[columns*rows];
        time = TimeUtils.millis();

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                textures[i * rows + j] = new TextureRegion(texture,
                        j*texture.getWidth()/columns,
                        i*texture.getHeight()/rows,
                        texture.getWidth()/columns,
                        texture.getHeight()/rows);
            }
        }
        currentTexture = 0;
        width = 15;
        height = 15;
    }

    @Override
    public void update(float delta) {

    }

    public void render(SpriteBatch batch){
        if(TimeUtils.millis() - time > 100 && isOver != true)
        {
            currentTexture++;
            if(currentTexture == columns*rows) {
                isOver = true;
                currentTexture--;
            }
            time = TimeUtils.millis();
        }
        batch.draw(textures[currentTexture],x - width/2,y - height/2,width,height);
    }

    @Override
    public void checkIfDestroy() {
        if(isOver){
            EntitiesManager.unregisterEntity(this);
        }
    }
}
