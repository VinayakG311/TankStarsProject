package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class missiles extends Sprite {

    private Texture texture;

    private int damage;
    public Body body;
    private World world;
    private int area;


    public missiles(Texture texture,World world, int damage, int area,int x,int y) {
        this.texture = texture;
        this.damage = damage;
        this.area = area;
        this.world=world;
        this.makebox(x,y);
        setBounds(x,y,25, 25);
        setRegion(texture);

    }
    public void update(float dt){

//        setPosition(body.getPosition().x-12, body.getPosition().y-12);
        float x = body.getPosition().x;
        float y = body.getPosition().y;
        setPosition(body.getPosition().x+body.getLinearVelocity().x*dt,body.getPosition().y+body.getLinearVelocity().y*dt);
        if(body.getLinearVelocity().x>0){
            body.applyForce(40.0f,10.0f,x,y,true);
        }
        else{
            body.applyForce(-40.0f,10.0f,x,y,true);
        }


    }
    private void makebox(int x,int y){

            BodyDef bodyDef = new BodyDef();
            bodyDef.position.set(x ,y);
            bodyDef.type = BodyDef.BodyType.DynamicBody;

            body = world.createBody(bodyDef);

            FixtureDef fixtureDef = new FixtureDef();
            CircleShape shape = new CircleShape();
            shape.setRadius(15);

            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
            body.setUserData("missile");

    }


}
