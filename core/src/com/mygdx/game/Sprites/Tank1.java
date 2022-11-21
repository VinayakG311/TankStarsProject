package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;


public class Tank1 {
    private Vector3 position;
    private Vector3 velocity;
    private Texture tank;

    public Tank1(int x,int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);

        tank = new Texture("tank1.png");
    }

    public void update(float dt){

        velocity.scl(dt);
        position.add(velocity.x,0,0);

        velocity.scl(1/dt);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public Texture getTank() {
        return tank;
    }

    public void movef(){
        velocity.x = 100;
        System.out.println(position.x);

    }

    public void moveb(){
        velocity.x = -100;
        System.out.println(position.x);

    }

    public void moves(){
        velocity.x = 0;
    }
}
