package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.core.MyGdxGame;
import com.mygdx.game.entity.ScrollingBackground;


public class EndScreen implements Screen{

    final MyGdxGame game;
    private Camera camera;
    private Long points;


    private Viewport viewport;
    private final int WORLD_WIDTH = (int)(72*1);
    private final int WORLD_HEIGHT = (int)(128*1);

    Label.LabelStyle label1Style;

    private ScrollingBackground background;

    Label label1;
    Label label2;

    public EndScreen(MyGdxGame game, Long points) {
        this.game = game;
        this.points = points;

        camera = new OrthographicCamera();

        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);

        background = new ScrollingBackground();


        label1Style = new Label.LabelStyle();
        label1Style.font = game.getFont();
        label1Style.fontColor = Color.WHITE;

        label1 = new Label("Game Over",label1Style);
        label1.setFontScale(0.20f);
        label1.setSize(WORLD_WIDTH,30);
        label1.setPosition(0,60);
        label1.setAlignment(Align.center);

        label2 = new Label("" + points,label1Style);
        label2.setFontScale(0.12f);
        label2.setSize(WORLD_WIDTH,18);
        label2.setPosition(0,50);
        label2.setAlignment(Align.center);


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

        camera.update();

        game.getBatch().setProjectionMatrix(camera.combined);
//        viewport.apply();
       //game.getBatch().setProjectionMatrix(viewport.getCamera().combined);

        //game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();

        background.render(game.getBatch(), delta);

        game.getFont().setColor(1,1,1, 1);
        game.getFont().getData().setScale(1.f);

        label1.draw(game.getBatch(), 1.f);
        label2.draw(game.getBatch(), 1.f);



//        game.getFont().draw(game.getBatch(),
//                "GAME OVER ",
//                WORLD_WIDTH/4,
//                WORLD_HEIGHT/2,
//                WORLD_WIDTH,
//                1,
//                false);
//        game.getFont().getData().setScale(0.3f);
//        game.getFont().draw(game.getBatch(),
//                "Tap anywhere to play again!",
//                5,
//                WORLD_HEIGHT/4,
//                WORLD_WIDTH,
//                1,
//                false);
        //game.getFont().draw(game.getBatch(), "Tap anywhere to play again!", 0, 100);
        game.getBatch().end();

        if (Gdx.input.isTouched()) {
            //game.setScreen(new GameScreen(game));
            game.setScreen(new MenuScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        game.getBatch().setProjectionMatrix(camera.combined);
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
