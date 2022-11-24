package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.trialMapScreen;

public class Tanktry extends Sprite {
    public World world;
    public Body b2body;
    private Texture player1;

    public Tanktry(World world, trialMapScreen screen){

        this.world = world;
        defTank();
        player1 = screen.getTank_player1();
        setBounds(50,320,player1.getWidth(), player1.getHeight());
        setRegion(player1);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth() /2, b2body.getPosition().y-getHeight() / 2);
    }

    public void defTank(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(3350 ,320 );
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
