package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screen.GameScreen;

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


    public ScrollingBackground() {
        maxScrollingSpeed = GameScreen.WORLD_HEIGHT /4.f;
    }

    public void render(SpriteBatch batch, float delta){
        for(int layer = 0; layer < backgroundOffsets.length ; layer++){

            backgroundOffsets[layer] += delta * maxScrollingSpeed / (float)Math.pow(2.0, (double)(backgroundOffsets.length - layer-1));

            if(backgroundOffsets[layer] > GameScreen.WORLD_HEIGHT){
                backgroundOffsets[layer] -= GameScreen.WORLD_HEIGHT;
            }

            batch.draw(backgrounds[layer],0,-backgroundOffsets[layer],GameScreen.WORLD_WIDTH,GameScreen.WORLD_HEIGHT);
            batch.draw(backgrounds[layer],0,-backgroundOffsets[layer] + GameScreen.WORLD_HEIGHT,GameScreen.WORLD_WIDTH, GameScreen.WORLD_HEIGHT);
        }
    }
}
