package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Screens.homeScreen;
import com.mygdx.game.states.gameStateManager;

public class tankStars extends Game {

	Texture img;
	public SpriteBatch sprite;
	public int ppm = 100;
	private gameStateManager GameStateManager;
	public Texture tank;



	@Override
	public void create () {
		sprite = new SpriteBatch();
		//setScreen(new homeScreen(this));
		setScreen(new MapScreen(this));


		//		GameStateManager = new gameStateManager();
//		Gdx.gl.glClearColor(1,0,0,1);
//		GameStateManager.push(new mainMenuState(GameStateManager));

	}

	@Override
	public void render () {
		super.render();
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		GameStateManager.update(Gdx.graphics.getDeltaTime());
//		GameStateManager.render(sprite);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}

	@Override
	public Screen getScreen() {
		return super.getScreen();
	}

	public tankStars() {
		super();
	}

	@Override
	public void dispose () {
		sprite.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

}
