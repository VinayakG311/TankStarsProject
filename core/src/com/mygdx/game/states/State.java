package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
    protected gameStateManager GameStateManager;
    protected OrthographicCamera camera;

    public State(gameStateManager GameStateManager){
        this.GameStateManager = GameStateManager;
        camera = new OrthographicCamera();
    }

    public abstract void update(float dt);
    public abstract void render(SpriteBatch sprite);
    public abstract void handleInput();
    public abstract void dispose();

}
