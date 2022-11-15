package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tankStars;

public class homeScreen extends ScreenAdapter {
    private SpriteBatch sb;
    private Sprite sprite;

    private tankStars game;

    public homeScreen(tankStars game) {
       this.game = game;
    }

    @Override
    public void show() {
        sb = new SpriteBatch();
        Texture img = new Texture("img.png");
        sprite = new Sprite(img);
        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new mainMenu(game));
                }
                return true;
            }
        });
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        sprite.draw(sb);
        sb.end();

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
        sb.dispose();
        sprite.getTexture().dispose();
    }
}
