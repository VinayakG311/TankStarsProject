package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class mainMenuState extends State{
    private final Texture backGround;
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    public mainMenuState(gameStateManager GameStateManager) {
        super(GameStateManager);
        backGround = new Texture("img.jpg");
        gamecam=new OrthographicCamera();
        gamePort=new FitViewport(850,500,gamecam);

        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sprite) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sprite.begin();
        sprite.draw(backGround,0,0,850,500);
        sprite.end();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            GameStateManager.set(new playState(GameStateManager));
        }
    }

    @Override
    public void dispose() {
        backGround.dispose();
    }
}
