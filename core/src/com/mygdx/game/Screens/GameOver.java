package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.tankStars;

public class GameOver implements Screen {
    private tankStars game;
    private BitmapFont font;

    private Texture GameOverImage1;
    private int result;
    public GameOver(tankStars game, int result){
        this.game=game;
        this.result=result;
        font=new BitmapFont();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(38/255.0f, 45/255.0f, 107/255.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        game.sprite.begin();
        if(result==0){
        font.draw(game.sprite, "player1 won",500 ,  500);
        }
        else{
            font.draw(game.sprite, "player2 won",500 ,  500);

        }

        game.sprite.end();

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
