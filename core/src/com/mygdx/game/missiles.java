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


    public missiles(Texture texture,World world, int damage, int area) {
        this.texture = texture;
        this.damage = damage;
        this.area = area;
        this.world=world;
        this.makebox();
        setBounds(500,320,25, 25);
        setRegion(texture);
    }
    public void update(float dt){

        setPosition(body.getPosition().x-12, body.getPosition().y-12);
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
