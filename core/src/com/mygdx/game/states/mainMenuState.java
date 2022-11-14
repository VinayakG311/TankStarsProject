package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class mainMenuState extends State{
    private Texture backGround;
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
