package com.mygdx.game.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.screen.EndScreen;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.MenuScreen;

public class MyGdxGame extends Game {

	private SpriteBatch batch;
	public BitmapFont font;
	public BitmapFont font2;



	@Override
	public void create() {
		batch = new SpriteBatch();

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 80;
		font = generator.generateFont(parameter);
		parameter.size = 40;
		font2 = generator.generateFont(parameter);

		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose(){
		super.dispose();
		batch.dispose();
		font.dispose();
		font2.dispose();
	}

	@Override
	public void render() {
		super.render();
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
