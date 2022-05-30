package com.mygdx.game.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screen.GameScreen;

public class MyGdxGame extends Game {

	private SpriteBatch batch;
	private BitmapFont font;



	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();

		/*gameScreen = new GameScreen();
		setScreen(gameScreen);*/

		this.setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
		//gameScreen.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
//		gameScreen.resize(width, height);
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public BitmapFont getFont() {
		return font;
	}
}
