package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.states.gameStateManager;
import com.mygdx.game.states.mainMenuState;

public class tankStars extends ApplicationAdapter {

	Texture img;
	public SpriteBatch sprite;
	private gameStateManager GameStateManager;
	
	@Override
	public void create () {
		sprite = new SpriteBatch();
		GameStateManager = new gameStateManager();

		Gdx.gl.glClearColor(1,0,0,1);
		GameStateManager.push(new mainMenuState(GameStateManager));

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		GameStateManager.update(Gdx.graphics.getDeltaTime());
		GameStateManager.render(sprite);
	}
	
	@Override
	public void dispose () {
		sprite.dispose();
	}
}
