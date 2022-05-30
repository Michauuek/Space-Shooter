package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrollingBackground {

    static private Texture[] backgrounds;

    static {
        backgrounds = new Texture[4];
        backgrounds[0] = new Texture(Gdx.files.internal("Starscape00.png"));
        backgrounds[1] = new Texture(Gdx.files.internal("Starscape01.png"));
        backgrounds[2] = new Texture(Gdx.files.internal("Starscape02.png"));
        backgrounds[3] = new Texture(Gdx.files.internal("Starscape03.png"));
    }

    private float[] backgroundOffsets = {0,0,0,0};
    private float maxScrollingSpeed;

    private int WORLD_WIDTH,WORLD_HEIGHT;

    public ScrollingBackground( int WORLD_WIDTH, int WORLD_HEIGHT) {
        this.WORLD_WIDTH = WORLD_WIDTH;
        this.WORLD_HEIGHT = WORLD_HEIGHT;
        maxScrollingSpeed = WORLD_HEIGHT /4.f;
    }

    public void render(SpriteBatch batch, float delta){
        for(int layer = 0; layer < backgroundOffsets.length ; layer++){

            backgroundOffsets[layer] += delta * maxScrollingSpeed / (float)Math.pow(2.0, (double)(backgroundOffsets.length - layer-1));

            if(backgroundOffsets[layer] > WORLD_HEIGHT){
                backgroundOffsets[layer] -= WORLD_HEIGHT;
            }

            batch.draw(backgrounds[layer],0,-backgroundOffsets[layer],WORLD_WIDTH,WORLD_HEIGHT);
            batch.draw(backgrounds[layer],0,-backgroundOffsets[layer] + WORLD_HEIGHT,WORLD_WIDTH,WORLD_HEIGHT);
        }
    }
}
