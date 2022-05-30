package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.core.MyGdxGame;
import com.mygdx.game.entity.ScrollingBackground;

public class EndScreen implements Screen{

    final MyGdxGame game;
    private Camera camera;


    private Viewport viewport;
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;

    private ScrollingBackground background;


    public EndScreen(MyGdxGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        background = new ScrollingBackground(WORLD_WIDTH, WORLD_HEIGHT);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keyCode) {

                if (keyCode == Input.Keys.ENTER) {
                    game.setScreen(new GameScreen(game));
                }

                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,0);

        //camera.update();

        //game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();

        background.render(game.getBatch(), delta);

        game.getFont().setColor(1,1,1, 1);
        game.getFont().getData().setScale(0.4f);


        game.getFont().draw(game.getBatch(), "GAME OVER ", WORLD_WIDTH/2-14, WORLD_HEIGHT/2);
        //game.getFont().draw(game.getBatch(), "Tap anywhere to play again!", 0, 100);
        game.getBatch().end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            //dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.getBatch().dispose();
        game.getFont().dispose();
    }
}
