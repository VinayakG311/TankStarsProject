package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Screens.PlayScreen;

public class Tank extends Sprite {
    public World world;
    public Body b2body;

    public Body character;
    public Body body;

    public OrthographicCamera gamecam;
    public Vector2 movement;
    float pos;
    private TextureRegion tankStand;

    private Texture tank;
    private Vector3 position;
    private Vector3 velocity;
    private Sprite sprite;

    public Tank(World world, float pos, PlayScreen screen,int x,int y){
        Box2D.init();
        this.world=world;
        this.pos=pos;
        gamecam=new OrthographicCamera(Gdx.graphics.getWidth()/100,Gdx.graphics.getHeight()/100);
//        gamecam = new OrthographicCamera(50,50);
        this.deftank();
        this.defground();
        this.hello();
        movement=new Vector2(50,50);
        tankStand = new TextureRegion(new Texture("tank.png"),0,0,566,340);
        setBounds(250,250,564/100,340/100 );

        setRegion(tankStand);
        tank=new Texture("tank.png");
        System.out.println(character.getPosition().x+" "+x);
        position= new Vector3(x,y,0);
        velocity=new Vector3(0,0,0);
        sprite = new Sprite(tank);
       // sprite.setBounds(300,200,25,25);
        sprite.setSize(25,25);

    }
    public void render(SpriteBatch batch){
        sprite.setPosition(300,230);
        sprite.draw(batch);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y-getHeight()/2);
        velocity.add(0,-15,0);
        velocity.scl(dt);
        position.add(0,0,0);
        velocity.scl(1/dt);
    }

    public void deftank(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(-1.5f,0);
        bdef.position.set(pos,-1.5f);
        bdef.type=BodyDef.BodyType.DynamicBody;
        // b2body=world.createBody(bdef);
        character = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();


        fdef.density=2.5f;
        fdef.friction = 0.25f;
        fdef.restitution=0.75f;

        CircleShape shape = new CircleShape();
        shape.setRadius(0.1f);
        fdef.shape=shape;
     //   b2body.createFixture(fdef);
        
        character.setUserData(this);
        character.createFixture(fdef);


        System.out.println(character.getUserData());

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

        shape.createChain(new Vector2[]{new Vector2(-500,-1.7f),new Vector2(500,-1.7f)});
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
        shape1.setAsBox(0.1f,0.1f,new Vector2(-0.7f,-1.6f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-0.9f,-1.56f),-5);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-0.55f,-1.62f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-1.6f,-1.6f),-25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-1.4f,-1.56f),5);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-1.81f,-1.62f),-25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-1.25f,-1.53f),5);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-1.12f,-1.54f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-1.9f,-1.69f),-25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-2.1f,-1.67f),5);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-2.28f,-1.67f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(1.5f,-1.6f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(1.7f,-1.6f),30f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(1.85f,-1.6f),30f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(1.99f,-1.6f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(2.12f,-1.6f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(2.27f,-1.6f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(2.47f,-1.6f),30f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(2.67f,-1.55f),35f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(2.87f,-1.55f),40f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(1.3f,-1.6f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(1.1f,-1.57f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(0.9f,-1.53f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(0.7f,-1.53f),5f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(0.5f,-1.5f),0f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(0.3f,-1.53f),0f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(0.1f,-1.57f),5f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-0.1f,-1.62f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-0.3f,-1.62f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-2.48f,-1.67f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-2.68f,-1.6f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-2.88f,-1.57f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);
        shape1.setAsBox(0.1f,0.1f,new Vector2(-3.0f,-1.62f),25f);
        fdef.shape=shape1;
        b2body.createFixture(fdef);

    }

    public Texture getTank() {
        return tank;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTankStand() {
        return tankStand;
    }
}
