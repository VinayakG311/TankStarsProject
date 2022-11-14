package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.hud;
import com.mygdx.game.tankStars;


public class PlayScreen implements Screen {
    private tankStars game;
    private final Texture backGround;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private hud hud;

    public PlayScreen(tankStars game){
        this.game=game;
        gamecam=new OrthographicCamera();
        gamePort=new FitViewport(800,480,gamecam);
        backGround = new Texture("background.jpg");
        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
        hud = new hud(game.sprite);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //game.sprite.setProjectionMatrix(gamecam.combined);
        game.sprite.begin();
        game.sprite.draw(backGround,0,0,850,500);
        game.sprite.end();
        game.sprite.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);

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
