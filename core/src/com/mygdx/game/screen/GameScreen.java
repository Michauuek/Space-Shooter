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

    private Score score;
    private Lives lives;

    private Array<Explosion> explosions;
    private Array<Projectile> playerProjectiles;
    private Array<EnemyShip> enemyShips;
    private Array<Projectile> enemyProjectiles;

    //private DelayedRemovalArray<EnemyShip> removalEnemyShip = new DelayedRemovalArray<>(enemyProjectiles);
    ShipSpawner spawner;
    Collisions col;


    public GameScreen(MyGdxGame game){

        this.game = game;

        camera = new OrthographicCamera();

        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);

        scrollingBackground = new ScrollingBackground(WORLD_WIDTH,WORLD_HEIGHT);


        playerProjectiles = new Array<>(false,10);
        enemyProjectiles = new Array<>(false,50);


        player = new Player(WORLD_WIDTH/2,3,WORLD_WIDTH, WORLD_HEIGHT, playerProjectiles);
        inputHandler = new InputHandler(player.getPlayerMovement(), camera);
        score = new Score();
        lives = new Lives();
        entitiesManager = new EntitiesManager();

        enemyShips = new Array<>(false,10);
        spawner = new ShipSpawner(enemyShips,enemyProjectiles,WORLD_WIDTH,WORLD_HEIGHT);
        explosions = new Array<>(false, 10);


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0,0,0,0);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        //game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();

        inputHandler.ListenForInput(delta);


        player.update(delta);
        spawner.generate();

        scrollingBackground.render(game.getBatch(),delta);

        player.render(game.getBatch());

       // updateEntities(delta); // !!!
        entitiesManager.UpdateAllEntities(delta,game.getBatch());


        //detect collisions
        //detectCollisions();

        lives.render(game.getBatch(), delta, 1,WORLD_HEIGHT-9);

        if(lives.isOver()){
            game.setScreen(new EndScreen(game, score.getPoints()));
        }

        game.getBatch().end();

    }

    private void detectCollisions() {

        //collision player's projectiles with enemies ship
        for (Iterator<Projectile> iter = playerProjectiles.iterator(); iter.hasNext(); ) {
            Projectile projectile = iter.next();

            for(EnemyShip ship : enemyShips){
                if (ship.collides(projectile)){
                    explosions.add(new Explosion(
                            ship.getRect().x + ship.getRect().width/2,
                            ship.getRect().y + ship.getRect().height/2,
                            new Texture(Gdx.files.internal("exp2_0.png")),
                            4,
                            4
                    ));
                    iter.remove();
                    score.addPoints();
                    enemyShips.removeValue(ship, true);
                }
            }
        }

        //collision enemies' projectiles with player's ship
        for (Iterator<Projectile> iter = enemyProjectiles.iterator(); iter.hasNext(); ) {
            Projectile projectile = iter.next();

            if (player.collides(projectile)){

                iter.remove();
                lives.loseLife();

            }
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

    private void updateEntities(float delta){
        for (Iterator<Projectile> iter = playerProjectiles.iterator(); iter.hasNext(); ) {
            Projectile projectile = iter.next();
            projectile.update(delta);
            projectile.render(game.getBatch());

            if (projectile.getRect().y > WORLD_HEIGHT) iter.remove();
        }

        for (Iterator<Projectile> iter = enemyProjectiles.iterator(); iter.hasNext(); ) {
            Projectile projectile = iter.next();
            projectile.update(delta);
            projectile.render(game.getBatch());

            if (projectile.getRect().y + projectile.getRect().height  < 0) iter.remove();
        }

        for (Iterator<EnemyShip> iter = enemyShips.iterator(); iter.hasNext(); ) {
            EnemyShip ship = iter.next();
            ship.update(delta);
            ship.render(game.getBatch());

            if (ship.getRect().y + ship.getRect().height  < 0){
                lives.loseLife();
                iter.remove();
            }
        }

        for (Iterator<Explosion> iter = explosions.iterator(); iter.hasNext(); ) {
            Explosion explosion = iter.next();
            explosion.render(game.getBatch());

            if (explosion.isOver()) iter.remove();
        }
    }
}
