package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Screens.trialMapScreen;

public class Tanktry extends Sprite {
    public World world;
    public Body body;
    private Texture player1;
    private double angle;

    public Tanktry(World world, trialMapScreen screen,int posx,int posy,Texture tank){

        this.world = world;
        createTank(posx,posy);
        player1 = tank;
        setBounds(300,320,player1.getWidth(), player1.getHeight());
        setRegion(player1);
        angle=45;
    }

    public void update(float dt){

        setPosition(body.getPosition().x-getWidth() /2, body.getPosition().y-getHeight() / 2);
    }

    public void createTank(int x,int y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x ,y );
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        body.setUserData("tank");
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }
}
