package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class Tanktry extends Sprite {
    public World world;
    public Body b2body;

    public Tanktry(World world){
        this.world = world;
        defTank();
    }

    public void defTank(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(50 ,320 );
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(20 );

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
