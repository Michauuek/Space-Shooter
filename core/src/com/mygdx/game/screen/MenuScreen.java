package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.core.MyGdxGame;
import com.mygdx.game.entity.ScrollingBackground;

public class MenuScreen implements Screen {

    final MyGdxGame game;
    private Camera camera;
    private ScrollingBackground background;


    private Viewport viewport;
    private final int WORLD_WIDTH = (int)(72*1);
    private final int WORLD_HEIGHT = (int)(128*1);

    Label.LabelStyle labelStyle;
    Label label;

    public MenuScreen(MyGdxGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        background = new ScrollingBackground(WORLD_WIDTH, WORLD_HEIGHT);

        labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getFont();
        labelStyle.fontColor = Color.WHITE;

        label = new Label("NEW GAME",labelStyle);
        label.setFontScale(0.25f);
        label.setSize(WORLD_WIDTH,30);
        label.setPosition(0,60);
        label.setAlignment(Align.center);


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
        ScreenUtils.clear(1,0.5f,0,0);

        //camera.update();

        game.getBatch().begin();

        background.render(game.getBatch(), delta);

        game.getFont().setColor(1,1,1, 1);
        game.getFont().getData().setScale(1.f);

        label.draw(game.getBatch(), 1.f);

        game.getBatch().end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
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

    }
}
