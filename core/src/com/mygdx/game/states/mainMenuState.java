package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class mainMenuState extends State{
    private final Texture backGround;

    public mainMenuState(gameStateManager GameStateManager) {
        super(GameStateManager);
        backGround = new Texture("background.jpg");

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.begin();
        sprite.draw(backGround,0,0,850,500);
        sprite.end();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {
        backGround.dispose();
    }
}
