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


    }
    public void deftank(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(0,1);
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
        shape.createChain(new Vector2[]{new Vector2(-500,0),new Vector2(500,0)});
        shape.setRadius(0.1f);
        fdef.shape=shape;
        b2body.createFixture(fdef);

    }
}
