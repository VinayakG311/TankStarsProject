package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Tank extends Sprite {
    public World world;
    public Body b2body;

    public OrthographicCamera gamecam;


    public Tank(World world){
        this.world=world;
        gamecam=new OrthographicCamera(Gdx.graphics.getWidth()/100,Gdx.graphics.getHeight()/100);
        this.deftank();
        this.defground();
        this.hello();


    }
    public void deftank(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(-1.5f,0);
        bdef.type=BodyDef.BodyType.DynamicBody;
        b2body=world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        fdef.density=2.5f;
        fdef.friction = 0.25f;
        fdef.restitution=0.75f;

        CircleShape shape = new CircleShape();
        shape.setRadius(0.1f);
        fdef.shape=shape;
        b2body.createFixture(fdef);

    }
    public void defground(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(0,0);
        bdef.type=BodyDef.BodyType.StaticBody;
        b2body=world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        fdef.density=2.5f;
        fdef.friction = 0.5f;
        fdef.restitution=0;

        ChainShape shape = new ChainShape();

        shape.createChain(new Vector2[]{new Vector2(-500,-1.5f),new Vector2(500,-1.5f)});
        shape.setRadius(0.1f);
        fdef.shape=shape;
        b2body.createFixture(fdef);

    }
    public void hello(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(0,0);
        bdef.type=BodyDef.BodyType.StaticBody;
        b2body=world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        fdef.density=2.5f;
        fdef.friction = 0.5f;
        fdef.restitution=0;
        PolygonShape shape1 = new PolygonShape();
        shape1.setAsBox(0.1f,0.1f,new Vector2(-0.7f,-1.4f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-0.9f,-1.36f),-5);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-0.55f,-1.42f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-1.6f,-1.4f),-25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-1.4f,-1.36f),5);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-1.81f,-1.42f),-25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-1.25f,-1.33f),5);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-1.12f,-1.32f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-1.9f,-1.47f),-25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-2.1f,-1.5f),5);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-2.28f,-1.52f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);

    }
}
