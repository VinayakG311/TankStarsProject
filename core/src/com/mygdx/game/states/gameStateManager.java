package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class gameStateManager{
    private static Stack<State> currState;
    public gameStateManager() {
        currState = new Stack<State>();
    }

    public void push(State state){
        currState.push(state);
    }

    public void pop(){
        currState.pop().dispose();
    }

    public static void set(State state){
        currState.pop().dispose();
        currState.push(state);
    }

    public void update(float dt){
        currState.peek().update(dt);
    }

    public void render(SpriteBatch sprite){

        currState.peek().render(sprite);
    }


}
