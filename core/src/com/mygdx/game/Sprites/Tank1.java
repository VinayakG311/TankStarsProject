package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;


public class Tank1 {
    private Vector3 position;
    private Vector3 velocity;
    private Texture tank;

    private int fuel;
    private Boolean isPlayer1 ;
    private Boolean isPlayer2 ;

    public int getFuel() {
        return fuel;
    }

    public Tank1(int x, int y, Texture tank, Boolean player1, Boolean player2){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        this.tank = tank;
        fuel=100;
        isPlayer1=player1;
        isPlayer2=player2;
    }

    public void update(float dt){
        System.out.println(fuel);
        if(fuel>0){
        velocity.scl(dt);
        position.add(velocity.x,0,0);
        velocity.scl(1/dt);
        fuel-=(Math.abs(velocity.x))/50;
        }



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
        if(isPlayer1) {
            velocity.x = 100;
        }
        if(isPlayer2){
            velocity.x=-100;
        }
        //System.out.println(position.x);

    }

    public void moveb(){
        if(isPlayer1) {
            velocity.x = -100;
        }
        if(isPlayer2){
            velocity.x=100;
        }

    }

    public void moves(){
        velocity.x = 0;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }
}
