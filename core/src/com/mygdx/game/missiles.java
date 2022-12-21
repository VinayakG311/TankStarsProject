package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

        setPosition(body.getPosition().x-12, body.getPosition().y-12);

        if(body.getLinearVelocity().x<0){
            System.out.println(body.getLinearVelocity().y);
            if(body.getLinearVelocity().y>0){
                body.setLinearVelocity(body.getLinearVelocity().x-50,body.getLinearVelocity().y+(15)*dt*10);
//                body.setLinearVelocity(body.getLinearVelocity().x-50,body.getLinearVelocity().y-40);
            }

            else{
                body.setLinearVelocity(body.getLinearVelocity().x-50,body.getLinearVelocity().y+(15)*dt*10);
            }
        }


        else {
            System.out.println(body.getLinearVelocity().y);
            if (body.getLinearVelocity().y > 0) {
                body.setLinearVelocity(body.getLinearVelocity().x + 50, body.getLinearVelocity().y + (15)*dt);
//                body.setLinearVelocity(body.getLinearVelocity().x + 50, body.getLinearVelocity().y - 40);
            } else {
                body.setLinearVelocity(body.getLinearVelocity().x + 50, body.getLinearVelocity().y+15*dt);
            }
        }

//        body.setLinearVelocity(body.getLinearVelocity().x,body.getLinearVelocity().y-40);

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
