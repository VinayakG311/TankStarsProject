package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tankStars;

public class homeScreen extends ScreenAdapter {
    private SpriteBatch sb;
    private Sprite sprite;
    private BitmapFont font;

    private tankStars game;
    private static homeScreen homeScreen= null;
    public static homeScreen getInstance(tankStars game){
        if(homeScreen==null){
            homeScreen=new homeScreen(game);
        }
        return homeScreen;
    }

    private homeScreen(tankStars game) {
       this.game = game;

        font = new BitmapFont();
       font.setColor(Color.NAVY);
       font.getData().setScale(2.5f,2.5f);
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
        font.draw(sb, "PRESS SPACE TO CONTINUE",352,45);

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
