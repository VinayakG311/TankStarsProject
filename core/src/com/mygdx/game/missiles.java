package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;

public class missiles {

    private Texture texture;

    private int damage;
    public Body body;
    private World world;
    private int area;


    public missiles(Texture texture,World world, int damage, int area) {
        this.texture = texture;
        this.damage = damage;
        this.area = area;
        this.world=world;
        this.makebox();
    }
    private void makebox(){

            BodyDef bodyDef = new BodyDef();
            bodyDef.position.set(650 ,400 );
            bodyDef.type = BodyDef.BodyType.DynamicBody;

            body = world.createBody(bodyDef);

            FixtureDef fixtureDef = new FixtureDef();
            CircleShape shape = new CircleShape();
            shape.setRadius(15);

            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);

    }


}
