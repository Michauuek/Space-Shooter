package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.core.MyGdxGame;
import com.mygdx.game.entity.*;
import com.mygdx.game.movement.Collisions;
import com.mygdx.game.movement.InputHandler;
import com.mygdx.game.repo.CollisionsRegistry;

import java.util.Iterator;

public class GameScreen implements Screen {

    final MyGdxGame game;
    private Camera camera;
    private Viewport viewport;
    public final static int WORLD_WIDTH = (int)(72*1);
    public final static int WORLD_HEIGHT = (int)(128*1);
    private ScrollingBackground scrollingBackground;

    private InputHandler inputHandler;
    private Player player;
    private EntitiesManager entitiesManager;
    private CollisonManager collisonManager;
    private CollisionsRegistry collisionsRegistry;

    private Score score;
    private Lives lives;

    //private DelayedRemovalArray<EnemyShip> removalEnemyShip = new DelayedRemovalArray<>(enemyProjectiles);
    ShipSpawner spawner;
    Collisions col;


    public GameScreen(MyGdxGame game){

        this.game = game;

        InitializeMembers();
        BindMembers();

        EntitiesManager.registerEntity(player);
    }

    private void InitializeMembers(){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        scrollingBackground = new ScrollingBackground();
        player = new Player(WORLD_WIDTH/2,3);
        inputHandler = new InputHandler(player.getPlayerMovement(),camera);
        score = new Score();
        lives = new Lives();
        entitiesManager = new EntitiesManager();
        collisonManager = new CollisonManager();
        collisionsRegistry = new CollisionsRegistry();
        spawner = new ShipSpawner();
    }

    private void BindMembers(){
        collisonManager.Bind(collisionsRegistry);
        collisionsRegistry.Bind(score);
        collisionsRegistry.Bind(lives);
        EnemyShip.Bind(lives);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0,0,0,0);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();

        spawner.generate();
        inputHandler.ListenForInput(delta);
        scrollingBackground.render(game.getBatch(),delta);
        entitiesManager.updateAllEntities(delta,game.getBatch());
        collisonManager.checkAllCollison();
        lives.render(game.getBatch(), delta, 1,WORLD_HEIGHT-9);

        if(lives.isOver()){
            game.setScreen(new EndScreen(game, score.getPoints()));
        }

        game.getBatch().end();
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
